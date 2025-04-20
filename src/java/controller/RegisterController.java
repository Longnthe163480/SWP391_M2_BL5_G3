package controller;

import dao.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterController extends HttpServlet {

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
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String confirmpassword=request.getParameter("confirmpass");
        String email=request.getParameter("email");
        String role=request.getParameter("role");
        int roleid = role.equals("Mentor") ? 2 : 1;
        AccountDAO dao=new AccountDAO();
        if(!password.equals(confirmpassword)){
            request.setAttribute("errorMsg", "Password and ConfirmPassword doesn't same");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }else if(dao.checkAccount(username, email)==false){
            request.setAttribute("errorMsg", "Username , Email is exist");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }else{
            dao.insertAccount(username, password, roleid, email);
            response.sendRedirect("Login.jsp?msg=success");
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