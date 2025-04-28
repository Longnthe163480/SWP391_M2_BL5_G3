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
import dao.PostDAO;
import entity.PostLike;
import entity.Account;

/**
 *
 * @author Administrator
 */
public class LikePost extends HttpServlet {
   
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
            
            // Get post ID from request
            String postIdStr = request.getParameter("postId");
            if (postIdStr == null || postIdStr.isEmpty()) {
                response.sendRedirect("ViewAllPost");
                return;
            }
            
            int postId = Integer.parseInt(postIdStr);
            PostDAO postDAO = new PostDAO();
            
            // Check if user has already liked the post
            boolean hasLiked = postDAO.hasLiked(postId, account.getId());
            
            if (hasLiked) {
                // Remove like
                postDAO.removeLike(postId, account.getId());
            } else {
                // Add like
                PostLike like = new PostLike();
                like.setPostId(postId);
                like.setAccountId(account.getId());
                postDAO.addLike(like);
            }
            
            // Redirect back to post detail
            response.sendRedirect("ViewPostDetail?id=" + postId);
        } catch (Exception e) {
            request.setAttribute("error", "Error processing like: " + e.getMessage());
            request.getRequestDispatcher("ViewPostDetail.jsp").forward(request, response);
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
        return "Like post controller";
    }// </editor-fold>

}
