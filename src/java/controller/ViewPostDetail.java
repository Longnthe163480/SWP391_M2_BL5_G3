/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.*;
import entity.*;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class ViewPostDetail extends HttpServlet {
   
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
            // Get post ID from request parameter
            String postIdStr = request.getParameter("id");
            if (postIdStr == null || postIdStr.isEmpty()) {
                response.sendRedirect("ViewAllPost");
                return;
            }
            int postId = Integer.parseInt(postIdStr);
            PostDAO postDAO = new PostDAO();
            // Get post details
            Post post = postDAO.getPostById(postId);
            if (post == null) {
                response.sendRedirect("ViewAllPost");
                return;
            }
            
            // Get comments for this post
            List<Comment> comments = postDAO.getCommentsByPostId(postId);
            // map accountId -> accountName
            Map<Integer, String> accountNameMap = new HashMap<>();
            AccountDAO accountDAO = new AccountDAO();
            MenteeDAO menteeDAO = new MenteeDAO();
            MentorDAO mentorDAO = new MentorDAO();
            for (Comment c : comments) {
                int accId = c.getAccountId();
                if (!accountNameMap.containsKey(accId)) {
                    String name = "Unknown";
                    try {
                        Mentee mentee = menteeDAO.getMenteebyAccID(accId);
                        if (mentee != null && mentee.getName() != null && !mentee.getName().isEmpty()) {
                            name = mentee.getName();
                        } else {
                            Mentor mentor = mentorDAO.getMentorbyAccID(accId);
                            if (mentor != null && mentor.getName() != null && !mentor.getName().isEmpty()) {
                                name = mentor.getName();
                            } else {
                                Account acc = accountDAO.getAccountByid(accId);
                                if (acc != null) name = acc.getAccountname();
                            }
                        }
                    } catch (Exception ex) {}
                    accountNameMap.put(accId, name);
                }
            }
            // Name Create Post
            if (!accountNameMap.containsKey(post.getAccountId())) {
                String name = "Unknown";
                try {
                    Mentee mentee = menteeDAO.getMenteebyAccID(post.getAccountId());
                    if (mentee != null && mentee.getName() != null && !mentee.getName().isEmpty()) {
                        name = mentee.getName();
                    } else {
                        Mentor mentor = mentorDAO.getMentorbyAccID(post.getAccountId());
                        if (mentor != null && mentor.getName() != null && !mentor.getName().isEmpty()) {
                            name = mentor.getName();
                        } else {
                            Account acc = accountDAO.getAccountByid(post.getAccountId());
                            if (acc != null) name = acc.getAccountname();
                        }
                    }
                } catch (Exception ex) {}
                accountNameMap.put(post.getAccountId(), name);
            }            
            // Get like
            int likeCount = postDAO.getLikeCount(postId);
            boolean hasLiked = false;
            Object accObj = request.getSession().getAttribute("account");
            if (accObj != null && accObj instanceof entity.Account) {
                int accId = ((entity.Account) accObj).getId();
                hasLiked = postDAO.hasLiked(postId, accId);
            }
            // Get Image 
            List<PostImage> postImages = postDAO.getPostImages(postId);
            request.setAttribute("postImages", postImages);
            // Set attributes for JSP
            request.setAttribute("post", post);
            request.setAttribute("comments", comments);
            request.setAttribute("accountNameMap", accountNameMap);
            request.setAttribute("likeCount", likeCount);
            request.setAttribute("hasLiked", hasLiked);
            // Forward to JSP
            request.getRequestDispatcher("ViewPostDetail.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error loading post: " + e.getMessage());
            request.getRequestDispatcher("ViewPostDetail.jsp").forward(request, response);
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
        return "View post detail controller";
    }// </editor-fold>
}
