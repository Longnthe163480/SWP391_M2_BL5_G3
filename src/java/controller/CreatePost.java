/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import dao.PostDAO;
import entity.Post;
import entity.PostImage;
import entity.Account;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.annotation.MultipartConfig;

/**
 *
 * @author Administrator
 */
@MultipartConfig
public class CreatePost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get current user from session
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");

            if (account == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Get post parameters
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String featuredStr = request.getParameter("featured");

            if (title == null || content == null || title.trim().isEmpty() || content.trim().isEmpty()) {
                request.setAttribute("error", "Title and content are required");
                request.getRequestDispatcher("CreatePost.jsp").forward(request, response);
                return;
            }

            // Create new post
            Post post = new Post();
            post.setAccountId(account.getId());
            post.setTitle(title);
            post.setContent(content);
            post.setFeatured(featuredStr != null && featuredStr.equals("on"));

            // Add post to database
            PostDAO postDAO = new PostDAO();
            int postId = postDAO.createPost(post);

            if (postId == -1) {
                request.setAttribute("error", "Error creating post");
                request.getRequestDispatcher("CreatePost.jsp").forward(request, response);
                return;
            }

            // Handle image uploads
            List<Part> fileParts = new ArrayList<>();
            for (Part part : request.getParts()) {
                if (part.getName().equals("images") && part.getSize() > 0) {
                    fileParts.add(part);
                }
            }

            // Lưu ảnh vào thư mục uploads và lưu đường dẫn vào DB
            String uploadPath = getServletContext().getRealPath("/uploads");
            java.io.File uploadDir = new java.io.File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            for (int i = 0; i < fileParts.size(); i++) {
                Part filePart = fileParts.get(i);
                String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
                String filePath = uploadPath + java.io.File.separator + fileName;
                filePart.write(filePath);
                // Lưu đường dẫn vào DB
                PostImage image = new PostImage();
                image.setPostId(postId);
                image.setImageUrl("uploads/" + fileName);
                image.setThumbnail(i == 0); // Ảnh đầu tiên là thumbnail
                postDAO.addPostImage(image);
            }

            // Redirect to post detail
            response.sendRedirect("ViewPostDetail?id=" + postId);
        } catch (Exception e) {
            request.setAttribute("error", "Error creating post: " + e.getMessage());
            request.getRequestDispatcher("CreatePost.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("CreatePost.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Create post controller";
    }// </editor-fold>

}
