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
import java.sql.Date;

public class EditMentorProfile extends HttpServlet {

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
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String accountid = request.getParameter("accountid");
        String mentor = request.getParameter("mentorid");
        String achievment = request.getParameter("achievment");
        String introduce = request.getParameter("introduce");
        String cost = request.getParameter("cost");
        int accid = Integer.parseInt(accountid);
        int mid = Integer.parseInt(mentor);
        float costi = Float.parseFloat(cost);
        Date birth = Date.valueOf(birthday);
        MentorDAO dao = new MentorDAO();
        String mess = null;
        Account a = dao.checkEmail(email);
        if (a != null && a.getId() != accid) {
            mess = "Email is exist";
            request.getRequestDispatcher("ViewMentorProfile?accmentorid=" + accid + "&mess=" + mess).forward(request, response);
        } else {
            dao.updateEmailMentorProfile(accid, email);
            dao.updateMorMentorProfile(mid, name, sex, address, phone, birth, introduce, achievment, costi);
            mess = "Edit is successful";
            request.getRequestDispatcher("ViewMentorProfile?accmentorid=" + accid + "&mess=" + mess).forward(request, response);

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
