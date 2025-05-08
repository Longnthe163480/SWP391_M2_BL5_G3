package controller;

import dao.MentorAnalyticsDAO;
import entity.Mentor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MentorAnalytics extends HttpServlet {
   
    private static final Logger logger = Logger.getLogger(MentorAnalytics.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Mentor mentor = (Mentor) session.getAttribute("getmentor");

        // Kiểm tra bảo mật
        if (mentor == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            MentorAnalyticsDAO analyticsDAO = new MentorAnalyticsDAO();

            // Lấy thống kê
            Map<String, Integer> requestStats = analyticsDAO.getRequestStatistics(mentor.getId());
            Map<Integer, Integer> ratingDistribution = analyticsDAO.getRatingDistribution(mentor.getId());

            // Đặt attributes
            request.setAttribute("requestStats", requestStats);
            request.setAttribute("ratingDistribution", ratingDistribution);

            // Forward tới JSP
            request.getRequestDispatcher("MentorAnalytics.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in MentorAnalyticsController for mentorId: " + mentor.getId(), e);
            request.setAttribute("errorMessage", "An error occurred while loading analytics. Please try again later.");
            request.getRequestDispatcher("MentorAnalytics.jsp").forward(request, response);
        }
    }
}