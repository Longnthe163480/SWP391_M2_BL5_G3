/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class UpdateHireRequestStatus extends HttpServlet {

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
        try {
            // Get parameters
            String requestIdStr = request.getParameter("requestId");
            String statusIdStr = request.getParameter("statusId");
            Object mentorObj = request.getSession().getAttribute("getmentor");
            int mentorId = -1;
            if (mentorObj != null) {
                try {
                    mentorId = (int) mentorObj.getClass().getMethod("getId").invoke(mentorObj);
                } catch (Exception ex) {
                    mentorId = -1;
                }
            }
            if (requestIdStr == null || statusIdStr == null || mentorId == -1) {
                response.sendRedirect("ViewMentorHireRequest?mentorid=" + mentorId);
                return;
            }
            int requestId = Integer.parseInt(requestIdStr);
            int statusId = Integer.parseInt(statusIdStr);
            
            // Update status
            MentorDAO dao = new MentorDAO();
            dao.updateHireRequestStatus(requestId, statusId);
            
            // Redirect back to the hire requests page
            response.sendRedirect("ViewMentorHireRequest?mentorid=" + mentorId);
            
        } catch (NumberFormatException e) {
            Object mentorObj = request.getSession().getAttribute("getmentor");
            int mentorId = -1;
            if (mentorObj != null) {
                try {
                    mentorId = (int) mentorObj.getClass().getMethod("getId").invoke(mentorObj);
                } catch (Exception ex) {
                    mentorId = -1;
                }
            }
            response.sendRedirect("ViewMentorHireRequest?mentorid=" + mentorId);
        } catch (Exception e) {
            Object mentorObj = request.getSession().getAttribute("getmentor");
            int mentorId = -1;
            if (mentorObj != null) {
                try {
                    mentorId = (int) mentorObj.getClass().getMethod("getId").invoke(mentorObj);
                } catch (Exception ex) {
                    mentorId = -1;
                }
            }
            response.sendRedirect("ViewMentorHireRequest?mentorid=" + mentorId);
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
        return "Servlet for updating hire request status";
    }// </editor-fold>

}
