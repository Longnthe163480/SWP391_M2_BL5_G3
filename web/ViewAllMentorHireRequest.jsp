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
public class ViewAllHireRequest extends HttpServlet {

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
        String index = request.getParameter("index");
        String menteeid = request.getParameter("menteeid");
        
        // Handle admin viewing all requests
        if (menteeid == null) {
            if (index == null) {
                index = "1";
            }
            int indexp = Integer.parseInt(index);

            MenteeDAO dao = new MenteeDAO();
            List<HireRequestlist> list = dao.getAllHireRequests(indexp);
            
            int total = dao.getTotalAllHireRequests();
            int end = total / 4;
            if (total % 4 != 0) {
                end++;
            }

            request.setAttribute("endpage", end);
            request.setAttribute("hirerequest", list);
            request.getRequestDispatcher("MyHireRequest.jsp").forward(request, response);
            return;
        }
        
        // Handle mentee viewing their own requests
        int id = Integer.parseInt(menteeid);
        if (index == null) {
            index = "1";
        }
        int indexp = Integer.parseInt(index);
        
        MenteeDAO dao = new MenteeDAO();
        List<HireRequestlist> list = dao.pagingMenteeHireRequest(id, indexp);

        int total = dao.getTotalMenteeHireRequest(id);
        int end = total / 4;
        if (total % 4 != 0) {
            end++;
        }

        request.setAttribute("endpage", end);
        request.setAttribute("hirerequest", list);
        request.getRequestDispatcher("MyHireRequest.jsp").forward(request, response);
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
