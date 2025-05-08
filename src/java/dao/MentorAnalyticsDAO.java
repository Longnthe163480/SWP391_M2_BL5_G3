package dao;

import dbcontext.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MentorAnalyticsDAO extends DBContext {
    private static final Logger logger = Logger.getLogger(MentorAnalyticsDAO.class.getName());

    // Lấy số lượng request theo trạng thái cho mentor
    public Map<String, Integer> getRequestStatistics(int mentorId) {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Pending", 0);
        stats.put("Accepted", 0);
        stats.put("Rejected", 0);
        stats.put("Completed", 0);

        String query = "SELECT statusid, COUNT(*) as count FROM hirerequest WHERE mentorid = ? GROUP BY statusid";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, mentorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int statusId = rs.getInt("statusid");
                    int count = rs.getInt("count");
                    // Ánh xạ statusid sang tên trạng thái
                    switch (statusId) {
                        case 1: stats.put("Accepted", count); break; // statusid=1: accept
                        case 2: stats.put("Rejected", count); break; // statusid=2: reject
                        case 3: stats.put("Pending", count); break; // statusid=3: not yet
                        // Nếu có statusid=4 là Completed, thêm case
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error in getRequestStatistics for mentorId: " + mentorId, e);
        }
        return stats;
    }

    // Lấy phân bố đánh giá theo mức sao
    public Map<Integer, Integer> getRatingDistribution(int mentorId) {
        Map<Integer, Integer> ratings = new HashMap<>();
        // Khởi tạo số lượng 0 cho tất cả mức sao (1-5)
        for (int i = 1; i <= 5; i++) {
            ratings.put(i, 0);
        }

        String query = "SELECT f.star, COUNT(*) as count " +
                      "FROM Feedback f " +
                      "JOIN feedbackanswer fa ON f.id = fa.feedbackid " +
                      "JOIN answer a ON fa.answerid = a.id " +
                      "JOIN mentorcoderequest mcr ON a.mentorcoderequestid = mcr.id " +
                      "WHERE mcr.mentorid = ? AND f.star BETWEEN 1 AND 5 " +
                      "GROUP BY f.star";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, mentorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int star = rs.getInt("star");
                    int count = rs.getInt("count");
                    ratings.put(star, count);
                    logger.log(Level.INFO, "Star: " + star + ", Count: " + count);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error in getRatingDistribution for mentorId: " + mentorId, e);
        }
        return ratings;
    }

    // Lấy danh sách feedback theo số sao và mentor với phân trang
    public Map<String, Object> getFeedbackByStar(int mentorId, int star, int page, int pageSize) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> feedbackList = new ArrayList<>();
        int totalFeedback = 0;

        // Đếm tổng số feedback
        String countQuery = "SELECT COUNT(*) as total " +
                           "FROM Feedback f " +
                           "JOIN feedbackanswer fa ON f.id = fa.feedbackid " +
                           "JOIN answer a ON fa.answerid = a.id " +
                           "JOIN mentorcoderequest mcr ON a.mentorcoderequestid = mcr.id " +
                           "WHERE mcr.mentorid = ? AND f.star = ?";
        try (PreparedStatement ps = connection.prepareStatement(countQuery)) {
            ps.setInt(1, mentorId);
            ps.setInt(2, star);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalFeedback = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error counting feedback for mentorId: " + mentorId + ", star: " + star, e);
        }

        // Lấy danh sách feedback với phân trang
        String query = "SELECT f.menteeid, f.star, f.comment, m.name as menteeName " +
                      "FROM Feedback f " +
                      "JOIN feedbackanswer fa ON f.id = fa.feedbackid " +
                      "JOIN answer a ON fa.answerid = a.id " +
                      "JOIN mentorcoderequest mcr ON a.mentorcoderequestid = mcr.id " +
                      "JOIN mentee m ON f.menteeid = m.id " +
                      "WHERE mcr.mentorid = ? AND f.star = ? " +
                      "ORDER BY f.id " +
                      "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, mentorId);
            ps.setInt(2, star);
            ps.setInt(3, (page - 1) * pageSize);
            ps.setInt(4, pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> feedback = new HashMap<>();
                    feedback.put("menteeId", rs.getInt("menteeid"));
                    feedback.put("star", rs.getInt("star"));
                    feedback.put("comment", rs.getString("comment"));
                    feedback.put("menteeName", rs.getString("menteeName"));
                    feedbackList.add(feedback);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error in getFeedbackByStar for mentorId: " + mentorId + ", star: " + star, e);
        }

        result.put("feedbackList", feedbackList);
        result.put("totalFeedback", totalFeedback);
        return result;
    }
}