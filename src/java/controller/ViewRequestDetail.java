/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.*;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;


public class ViewRequestDetail extends HttpServlet {

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
        String reid = request.getParameter("reid");
        int id = Integer.parseInt(reid);
        String menteeid = request.getParameter("menteeid");
        String mentorid = request.getParameter("mentorid");
        MenteeDAO dao = new MenteeDAO();
        MentorDAO mdao = new MentorDAO();
        if (menteeid != null) {
            List<Mentor> mentor = dao.getMentorOfRequest(id);
            CodeRequest crequest = dao.getAReqeustByID(id);
            List<Skill> skill = dao.getSkillARequest(id);
            request.getSession().setAttribute("mentor", mentor);
            request.getSession().setAttribute("coderequest", crequest);
            request.getSession().setAttribute("skill", skill);
            request.getRequestDispatcher("RequestDetail.jsp").forward(request, response);
            return;
        }
        if (mentorid != null) {
            int idmentor=Integer.parseInt(mentorid);
            Answer x=mdao.getAnswer(idmentor, id);
            CodeRequest crequest = dao.getAReqeustByID(id);
            List<Skill> skill = dao.getSkillARequest(id);
            request.getSession().setAttribute("answer", x);
            request.getSession().setAttribute("coderequest", crequest);
            request.getSession().setAttribute("skill", skill);
            request.getRequestDispatcher("RequestDetail.jsp").forward(request, response);
        }

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
