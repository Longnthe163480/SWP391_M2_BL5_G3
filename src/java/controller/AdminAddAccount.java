package controller;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminAddAccount")
public class AdminAddAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        request.setAttribute("roleList", dao.getRole());
        request.getRequestDispatcher("AdminAddAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accountname = request.getParameter("accountname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int roleid = Integer.parseInt(request.getParameter("roleid"));

            if (accountname == null || accountname.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
                request.setAttribute("error", "Please fill in all required fields");
                doGet(request, response);
                return;
            }

            AccountDAO dao = new AccountDAO();
            if (dao.checkEmail(email) != null) {
                request.setAttribute("error", "Email already exists");
                doGet(request, response);
                return;
            }

            int accountid = dao.insertAccountAndGetId(accountname, password, roleid, email);
            if (accountid > 0) {
                if (roleid == 2) { // Mentor
                    dao.insertMentor(accountid, "default", "default", "default", null, "M", "default", "default", "mentor1.jpg", 0f);
                } else if (roleid == 1) { // Mentee
                    dao.insertMentee(accountid, "default", "default", "default", null, "M", "default", "mentor1.jpg");
                }
                response.sendRedirect(request.getContextPath() + "/ViewAllAccount");
            } else {
                request.setAttribute("error", "Failed to create account");
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while creating account");
            doGet(request, response);
        }
    }
} 