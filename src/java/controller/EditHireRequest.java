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

/**
 *
 * @author legen
 */
public class EditHireRequest extends HttpServlet {

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
        String hid = request.getParameter("hirereid");
        String menteeid = request.getParameter("menteeid");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String mentorid = request.getParameter("choosementor");
        int id = Integer.parseInt(hid);
        MenteeDAO dao = new MenteeDAO();      
        if (menteeid == null && title == null && content == null && mentorid == null) {
            HireRequest list = dao.getHireRequestbyid(id);  
            request.setAttribute("requesthire", list);
            request.getRequestDispatcher("EditHireRequest.jsp").forward(request, response);
        } else {
            HireRequest list = dao.getHireRequestbyid(id);
            if (mentorid == null) {
                mentorid = String.valueOf(list.getMentorid());
            }
            int idmentee = Integer.parseInt(menteeid);
            int idmentor = Integer.parseInt(mentorid);
            List<HireRelationship> rela = dao.getHireRelationship();
            for (HireRelationship h : rela) {
                if (h.getMenteeid() == id && h.getMentorid() == idmentor) {
                    request.setAttribute("error", "You are hiring this mentor,so you can't edit");
                    request.getRequestDispatcher("EditHireRequest.jsp").forward(request, response);
                    return;
                }
            }
            dao.updateHireRequest(id, idmentee, idmentor, title, content);
            HireRequest list2 = dao.getHireRequestbyid(id); 
            request.setAttribute("requesthire", list2);
            request.setAttribute("done", "Edit sucess");
            request.getRequestDispatcher("EditHireRequest.jsp").forward(request, response);
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
