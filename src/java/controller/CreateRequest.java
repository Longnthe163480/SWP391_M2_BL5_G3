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
 * @author legen
 */
public class CreateRequest extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
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
        processRequest(request, response);
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
        String menteeid=request.getParameter("menteeid");       
        String title=request.getParameter("title");
        String deadline=request.getParameter("deadline");       
        String content=request.getParameter("content");
        String[] mentorid=request.getParameterValues("choosementor");
        String[] skill=request.getParameterValues("skill");
        if(mentorid==null||skill==null){
             request.setAttribute("error", "you forget choosse mentor or skill");
        request.getRequestDispatcher("CreateRequest.jsp").forward(request, response);
        }else{
        int id=Integer.parseInt(menteeid);
        Date dead=Date.valueOf(deadline);
        MenteeDAO dao=new MenteeDAO();
        
        dao.inserCodeRequest(id, title, content, dead);
        CodeRequest coderequest=dao.getNewInsertReqeust();
        for (String s : mentorid) {
            int a=Integer.parseInt(s);
            dao.inserMentorCodeRequest(coderequest.getId(),a );
        }
        for (String m : skill) {
            int b=Integer.parseInt(m);
            dao.inserCodeRequestSkill(coderequest.getId(),b );
        }
        request.setAttribute("done", "Create sucess");
        request.getRequestDispatcher("CreateRequest.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

