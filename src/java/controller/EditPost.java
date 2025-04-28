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
import dao.PostDAO;
import entity.Post;
import entity.PostImage;
import java.io.File;
import java.util.List;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

/**
 *
 * @author Administrator
 */
@MultipartConfig(maxFileSize = 5 * 1024 * 1024) // 5MB
public class EditPost extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditPost</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditPost at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect("ViewAllPost");
            return;
        }
        int id = Integer.parseInt(idStr);
        PostDAO dao = new PostDAO();
        Post post = dao.getPostById(id);
        if (post == null) {
            response.sendRedirect("ViewAllPost");
            return;
        }
        List<PostImage> images = dao.getPostImages(id);
        PostImage postImage = images.isEmpty() ? null : images.get(0);
        request.setAttribute("post", post);
        request.setAttribute("postImage", postImage);
        request.getRequestDispatcher("EditPost.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        PostDAO dao = new PostDAO();
        Post post = dao.getPostById(id);
        if (post == null) {
            response.sendRedirect("ViewAllPost");
            return;
        }
        post.setTitle(title);
        post.setContent(content);
        // Có thể thêm validate ở đây
        dao.updatePost(post);
        // Xử lý ảnh
        List<PostImage> images = dao.getPostImages(id);
        PostImage oldImage = images.isEmpty() ? null : images.get(0);
        Part imagePart = request.getPart("image");
        String fileName = imagePart != null ? imagePart.getSubmittedFileName() : null;
        if (fileName != null && !fileName.isEmpty() && imagePart.getSize() > 0) {
            // Xóa ảnh cũ nếu có
            if (oldImage != null) {
                dao.deletePostImage(oldImage.getId());
                // Xóa file vật lý nếu cần
                String oldPath = getServletContext().getRealPath("/") + oldImage.getImageUrl();
                File f = new File(oldPath);
                if (f.exists()) f.delete();
            }
            // Lưu file mới
            String uploadPath = getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            String newFileName = System.currentTimeMillis() + "_" + fileName;
            String filePath = uploadPath + File.separator + newFileName;
            imagePart.write(filePath);
            // Lưu đường dẫn vào DB (ví dụ: /uploads/xxx.jpg)
            PostImage newImage = new PostImage();
            newImage.setPostId(id);
            newImage.setImageUrl("uploads/" + newFileName);
            newImage.setThumbnail(true);
            dao.addPostImage(newImage);
        }
        request.setAttribute("success", "Cập nhật bài viết thành công!");
        // Load lại post và ảnh mới nhất
        post = dao.getPostById(id);
        images = dao.getPostImages(id);
        PostImage postImage = images.isEmpty() ? null : images.get(0);
        request.setAttribute("post", post);
        request.setAttribute("postImage", postImage);
        request.getRequestDispatcher("EditPost.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
