/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.PostDAO;
import entity.Account;
import entity.Post;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class MyPost extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            
            // Get page parameter
            String indexStr = request.getParameter("index");
            int tag = 1;
            if (indexStr != null && !indexStr.isEmpty()) {
                tag = Integer.parseInt(indexStr);
            }
            // Set page size
            int pageSize = 6;
            // Get posts with pagination
            PostDAO postDAO = new PostDAO();
            List<Post> posts = postDAO.getPostsByAccountId(account.getId(), tag, pageSize);
            // Calculate total pages
            int totalPosts = postDAO.getTotalPostsByAccountId(account.getId());
            int endpage = totalPosts / pageSize;
            if (totalPosts % pageSize != 0) endpage++;
            // Set attributes for JSP
            request.setAttribute("posts", posts);
            request.setAttribute("tag", tag);
            request.setAttribute("endpage", endpage);
            // Forward to JSP
            request.getRequestDispatcher("MyPost.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error loading posts: " + e.getMessage());
            request.getRequestDispatcher("MyPost.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
