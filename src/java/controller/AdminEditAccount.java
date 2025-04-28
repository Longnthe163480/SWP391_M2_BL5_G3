package controller;

import dao.AccountDAO;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminEditAccount")
public class AdminEditAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null) {
            response.sendRedirect("AccountList.jsp");
            return;
        }
        AccountDAO dao = new AccountDAO();
        Account acc = dao.getAccountByid(Integer.parseInt(id));
        request.setAttribute("account", acc);
        request.setAttribute("roleList", dao.getRole());
        request.getRequestDispatcher("AdminEditAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String accountname = request.getParameter("accountname");
            String email = request.getParameter("email");
            int roleid = Integer.parseInt(request.getParameter("roleid"));
            String password = request.getParameter("password");

            if (accountname == null || accountname.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
                request.setAttribute("error", "Please fill in all required fields");
                doGet(request, response);
                return;
            }

            AccountDAO dao = new AccountDAO();
            boolean success;
            if (password != null && !password.trim().isEmpty()) {
                success = dao.updateAccountByAdminWithPassword(id, accountname, email, roleid, password);
            } else {
                success = dao.updateAccountByAdmin(id, accountname, email, roleid);
            }
            if (success) {
                response.sendRedirect(request.getContextPath() + "/ViewAllAccount");
            } else {
                request.setAttribute("error", "Failed to update account");
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while updating account");
            doGet(request, response);
        }
    }
}