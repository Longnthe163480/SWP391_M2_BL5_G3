package controller;

import dao.*;
import util.*;
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
        // Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpass");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String fullname = request.getParameter("fullname");

        // Khởi tạo Validation
        Validation validation = new Validation();
        PasswordUtil passwordUtil = new PasswordUtil();

        // Kiểm tra input sử dụng Validation
        String errorMsg = validation.validateSignUpInput(username, password, confirmpassword, email);
        
        if (errorMsg.isEmpty() && (fullname == null || !validation.validateFullname(fullname))) {
            errorMsg = "Fullname is invalid. It must start with a letter and contain only letters, spaces, or dots.";
        }
        
        if (!errorMsg.isEmpty()) {
            // Nếu có lỗi từ Validation, gửi thông báo lỗi về Register.jsp
            request.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } else {
            String hashedPassword = passwordUtil.hashPasswordMD5(password);
            if (hashedPassword == null) {
                request.setAttribute("errorMsg", "Error processing password. Please try again.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
                return;
            }
            // Nếu không có lỗi, xác định roleid và chèn tài khoản
            int roleid = role.equals("Mentor") ? 2 : 1;
            AccountDAO dao = new AccountDAO();
            int accountid = dao.insertAccountAndGetId(username, hashedPassword, roleid, email);
   if (accountid > 0) {
       if (roleid == 2) { // Mentor
           dao.insertMentor(accountid, fullname, "default", "default", null, "M", "default", "default", "mentor1.jpg", 0f);
       } else if (roleid == 1) { // Mentee
           dao.insertMentee(accountid, fullname, "default", "default", null, "M", "default", "mentee1.jpg");
       }
       response.sendRedirect("Login.jsp?msg=success");
   } else {
       request.setAttribute("errorMsg", "Registration failed. Please try again.");
       request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
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