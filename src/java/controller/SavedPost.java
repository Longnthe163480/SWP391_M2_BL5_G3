/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.PostDAO;
import entity.Account;
import entity.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class SavedPost extends HttpServlet {
   
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
            out.println("<title>Servlet SavedPost</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SavedPost at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String indexStr = request.getParameter("index");
        int tag = 1;
        if (indexStr != null && !indexStr.isEmpty()) {
            tag = Integer.parseInt(indexStr);
        }
        int pageSize = 6;
        PostDAO postDAO = new PostDAO();
        List<Post> allSavedPosts = postDAO.getSavedPostsByAccountId(account.getId());
        int totalPosts = allSavedPosts.size();
        int endpage = totalPosts / pageSize;
        if (totalPosts % pageSize != 0) endpage++;
        int from = (tag - 1) * pageSize;
        int to = Math.min(from + pageSize, totalPosts);
        List<Post> posts = allSavedPosts.subList(from, to);
        request.setAttribute("posts", posts);
        request.setAttribute("tag", tag);
        request.setAttribute("endpage", endpage);
        request.setAttribute("totalPosts", totalPosts);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("SavedPost.jsp").forward(request, response);
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
