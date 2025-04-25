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
import java.util.List;

/**
 *
 * @author Administrator
 */
public class CreateHireRequest extends HttpServlet {
   
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
            out.println("<title>Servlet CreateHireRequest</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateHireRequest at " + request.getContextPath () + "</h1>");
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
        String menteeid=request.getParameter("menteeid"); 
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        String mentorid=request.getParameter("choosementor");
        
        if(mentorid==null){
             request.setAttribute("error", "you forget choosse mentor");
        request.getRequestDispatcher("CreateHireRequest.jsp").forward(request, response);
        }else{
            MenteeDAO dao=new MenteeDAO();
            int id=Integer.parseInt(menteeid);
            int idmentor=Integer.parseInt(mentorid);
            List<HireRelationship> rela=dao.getHireRelationship();
            for (HireRelationship h : rela) {
                    if (h.getMenteeid() == id && h.getMentorid() == idmentor) {
                        request.setAttribute("error", "You are hiring this mentor");
                        request.getRequestDispatcher("CreateHireRequest.jsp").forward(request, response);
                        return;
                    }
            }    
        dao.inserHireRequest(id,idmentor, title, content);
        request.setAttribute("done", "Create sucess");
        request.getRequestDispatcher("CreateHireRequest.jsp").forward(request, response);
        
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
