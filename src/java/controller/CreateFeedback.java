/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.MenteeDAO;
import entity.Feedback;
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
public class CreateFeedback extends HttpServlet {
   
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
            out.println("<title>Servlet CreateFeedback</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateFeedback at " + request.getContextPath () + "</h1>");
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
        String menteeid = request.getParameter("menteeid");
        String answeid = request.getParameter("answerid");
        String star = request.getParameter("star");
        String comment = request.getParameter("comment");
        MenteeDAO dao = new MenteeDAO();
        if (menteeid == null && star == null && comment == null) {
            request.setAttribute("answerid", answeid);
            request.getRequestDispatcher("CreateFeedback.jsp").forward(request, response);
        } else {
            if (star == null) {
                request.setAttribute("error", "you don't choose the star");
                request.getRequestDispatcher("CreateFeedback.jsp").forward(request, response);
            } else {
                int idmentee = Integer.parseInt(menteeid);
                int stars = Integer.parseInt(star);
                int answer = Integer.parseInt(answeid);
                dao.createFeedback(idmentee, stars, comment);
                Feedback f = dao.getfeedbackadd();
                dao.createFeedbackAnswer( f.getId(),answer);
                request.setAttribute("done", "Create success");
                request.getRequestDispatcher("CreateFeedback.jsp").forward(request, response);
            }
        }
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
