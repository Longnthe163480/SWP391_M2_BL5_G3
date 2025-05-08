<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Feedback Details - ${star} Star</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container { margin-top: 20px; }
        .error-message { display: none; }
        .pagination { margin-top: 20px; }
    </style>
</head>
<body>
    <%@include file="guest/Header.jsp" %>
    <div class="container">
        <h2 class="mb-4">Feedback Details - ${star} Star${star > 1 ? 's' : ''}</h2>

        <!-- Thông báo lỗi -->
        <c:if test="${errorMessage != null}">
            <div class="alert alert-danger error-message">${errorMessage}</div>
        </c:if>

        <!-- Bảng danh sách feedback -->
        <c:choose>
            <c:when test="${not empty feedbackList}">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Mentee Name</th>
                            <th>Star</th>
                            <th>Comment</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="feedback" items="${feedbackList}" varStatus="loop">
                            <tr>
                                <td>${(currentPage - 1) * 20 + loop.count}</td>
                                <td>${feedback.menteeName}</td>
                                <td>${feedback.star}</td>
                                <td>${feedback.comment}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Phân trang -->
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="feedback-details?mentorId=${mentorId}&star=${star}&page=${currentPage - 1}">Previous</a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="feedback-details?mentorId=${mentorId}&star=${star}&page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="feedback-details?mentorId=${mentorId}&star=${star}&page=${currentPage + 1}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </c:when>
            <c:otherwise>
                <p>No feedback available for ${star} star${star > 1 ? 's' : ''}.</p>
            </c:otherwise>
        </c:choose>

        <!-- Nút quay lại -->
        <a href="mentor-analytics" class="btn btn-secondary mt-3">Back to Analytics</a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>