package controller;

import dao.*;
import entity.*;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import util.PasswordUtil;

public class LoginController extends HttpServlet {

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
        request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        AccountDAO dao = new AccountDAO();
        MentorDAO mentordao = new MentorDAO();
        MenteeDAO menteedao = new MenteeDAO();
        PasswordUtil passwordUtil = new PasswordUtil();
        
        try {
            // Kiểm tra trạng thái forgoted
            Boolean forgotedStatus = dao.getForgotedStatusByUsername(user);
            if (forgotedStatus == null) {
                request.setAttribute("err", "Tài khoản không tồn tại");
                request.setAttribute("username", user);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                return;
            }

            // Xử lý mật khẩu dựa trên forgoted
            String passwordToCheck = forgotedStatus ? pass : passwordUtil.hashPasswordMD5(pass);

            // Kiểm tra tài khoản với mật khẩu đã xử lý
            Account acc = dao.getAccount(user, passwordToCheck);
            if (acc == null) {
                request.setAttribute("err", "Sai tên đăng nhập hoặc mật khẩu");
                request.setAttribute("username", user); // Giữ lại username
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
               // Ngăn chặn session fixation
                request.getSession().invalidate();
               HttpSession session = request.getSession(true);
                
                int role = dao.checkrole(acc.getId());
                if (role == 2) {
                    Mentor mentor = mentordao.getMentorbyAccID(acc.getId());
                    session.setAttribute("getmentor", mentor);
                }
                if (role == 1) {
                    Mentee mentee = menteedao.getMenteebyAccID(acc.getId());
                    List<Mentor> list = mentordao.getAllMentor();
                    List<Skill> listskill = mentordao.getallskill();
                    session.setAttribute("getmentee", mentee);
                    session.setAttribute("listallmentor", list);
                    session.setAttribute("listallskill", listskill);
                }
                session.setAttribute("account", acc);
                response.sendRedirect("ViewTop3Mentor");
            }
        } catch (Exception e) {
            request.setAttribute("err", "Đã xảy ra lỗi. Vui lòng thử lại sau.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Xử lý đăng nhập người dùng";
    }
}