package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.PostDAO;
import entity.Account;
import java.io.IOException;

public class DeletePost extends HttpServlet {
   
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
            String postIdStr = request.getParameter("id");
            if (postIdStr == null || postIdStr.isEmpty()) {
                response.sendRedirect("ViewAllPost");
                return;
            }
            
            int postId = Integer.parseInt(postIdStr);
            
            // Get post from database
            PostDAO postDAO = new PostDAO();
            
            // Check if post exists and belongs to current user
            if (postDAO.getPostById(postId) == null || 
                postDAO.getPostById(postId).getAccountId() != account.getId()) {
                response.sendRedirect("ViewAllPost");
                return;
            }
            
            // Delete post
            postDAO.deletePostAndRelated(postId);
            
            // Redirect to post list
            response.sendRedirect("ViewAllPost");
        } catch (Exception e) {
            request.setAttribute("error", "Error deleting post: " + e.getMessage());
            request.getRequestDispatcher("ViewAllPost.jsp").forward(request, response);
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Delete post controller";
    }
} 