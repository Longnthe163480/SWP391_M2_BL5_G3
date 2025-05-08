package controller;

import dao.MentorAnalyticsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "FeedbackDetailsController", urlPatterns = {"/feedback-details"})
public class FeedbackDetailsController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(FeedbackDetailsController.class.getName());
    private static final int PAGE_SIZE = 20;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        entity.Mentor mentor = (entity.Mentor) session.getAttribute("getmentor");

        // Kiểm tra bảo mật
        if (mentor == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            // Lấy tham số mentorId, star và page
            int mentorId = Integer.parseInt(request.getParameter("mentorId"));
            int star = Integer.parseInt(request.getParameter("star"));
            int page = 1;
            String pageParam = request.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                page = Integer.parseInt(pageParam);
                if (page < 1) page = 1;
            }

            // Kiểm tra quyền truy cập
            if (mentorId != mentor.getId()) {
                request.setAttribute("errorMessage", "You are not authorized to view this feedback.");
                request.getRequestDispatcher("MentorAnalytics.jsp").forward(request, response);
                return;
            }

            MentorAnalyticsDAO analyticsDAO = new MentorAnalyticsDAO();
            Map<String, Object> result = analyticsDAO.getFeedbackByStar(mentorId, star, page, PAGE_SIZE);
            List<Map<String, Object>> feedbackList = (List<Map<String, Object>>) result.get("feedbackList");
            int totalFeedback = (int) result.get("totalFeedback");
            int totalPages = (int) Math.ceil((double) totalFeedback / PAGE_SIZE);

            // Đặt attributes
            request.setAttribute("feedbackList", feedbackList);
            request.setAttribute("star", star);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("mentorId", mentorId);

            // Forward tới JSP
            request.getRequestDispatcher("FeedbackDetails.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid mentorId, star, or page parameter", e);
            request.setAttribute("errorMessage", "Invalid parameters.");
            request.getRequestDispatcher("MentorAnalytics.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in FeedbackDetailsController for mentorId: " + mentor.getId(), e);
            request.setAttribute("errorMessage", "An error occurred while loading feedback details. Please try again later.");
            request.getRequestDispatcher("MentorAnalytics.jsp").forward(request, response);
        }
    }
}