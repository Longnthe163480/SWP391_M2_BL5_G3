/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.*;
import entity.*;
import java.sql.Date;
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
public class EditFeedback extends HttpServlet {
   
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
            out.println("<title>Servlet EditFeedback</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditFeedback at " + request.getContextPath () + "</h1>");
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
        String feedbackid=request.getParameter("feedbackid");
        String star=request.getParameter("star");
        String comment=request.getParameter("comment");
        int id=Integer.parseInt(feedbackid);
        MenteeDAO dao=new MenteeDAO();
        Feedback f =dao.getfeedbackbyid(id);
        if(star==null&&comment==null){
            request.setAttribute("feedback",f);
            request.getRequestDispatcher("EditFeedback.jsp").forward(request, response);
        }else{
            if(star==null){
                Feedback a =dao.getfeedbackbyid(id);
                request.setAttribute("feedback",a);
                request.setAttribute("error","you don't change star");
                request.getRequestDispatcher("EditFeedback.jsp").forward(request, response);
            }else{
                int s=Integer.parseInt(star);
                dao.updateFeedback(id,s,comment);
                Feedback b =dao.getfeedbackbyid(id);
                request.setAttribute("feedback",b);
                request.setAttribute("done","Edit Success");
                request.getRequestDispatcher("EditFeedback.jsp").forward(request, response);
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
