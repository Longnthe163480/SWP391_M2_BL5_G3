<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mentor Analytics</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .loading-spinner { display: none; text-align: center; padding: 20px; }
        .error-message { display: none; }
    </style>
</head>
<body>
    <%@include file="guest/Header.jsp" %>
    <div class="container mt-4">
        <h2 class="mb-4">Mentor Analytics Dashboard</h2>

        <!-- Thông báo lỗi -->
        <c:if test="${errorMessage != null}">
            <div class="alert alert-danger error-message">${errorMessage}</div>
        </c:if>

        <!-- Loading spinner -->
        <div class="loading-spinner">
            <div class="spinner-border" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>

        <!-- Thống kê tổng quan -->
        <div class="row mb-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Total Requests</h5>
                        <p class="card-text h3">
                            <c:set var="totalRequests" value="${requestStats['Pending'] + requestStats['Accepted'] + requestStats['Rejected'] + requestStats['Completed']}" />
                            ${totalRequests}
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Accepted Rate</h5>
                        <p class="card-text h3">
                            <c:set var="accepted" value="${requestStats['Accepted'] != null ? requestStats['Accepted'] : 0}" />
                            <c:set var="rejected" value="${requestStats['Rejected'] != null ? requestStats['Rejected'] : 0}" />
                            <c:set var="total" value="${accepted + rejected}" />
                            <c:choose>
                                <c:when test="${total > 0}">
                                    ${String.format("%.1f", (accepted * 100.0 / total))}%
                                </c:when>
                                <c:otherwise>N/A</c:otherwise>
                            </c:choose>
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Biểu đồ Request Status -->
        <div class="row mb-4">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Request Status Distribution</h5>
                        <canvas id="requestChart"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bảng phân bố đánh giá -->
        <div class="row mb-4">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Rating Distribution</h5>
                        <c:choose>
                            <c:when test="${not empty ratingDistribution}">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Rating</th>
                                            <th>Number of Feedbacks</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach begin="1" end="5" var="star">
                                            <tr>
                                                <td>${star} Star${star > 1 ? 's' : ''}</td>
                                                <td>${ratingDistribution[star]}</td>
                                                <td>
                                                    <c:if test="${ratingDistribution[star] > 0}">
                                                        <a href="feedback-details?mentorId=${sessionScope.getmentor.id}&star=${star}" class="btn btn-primary btn-sm">View Feedback</a>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <p>No feedback available.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Hiển thị spinner khi tải
        document.querySelector('.loading-spinner').style.display = 'block';

        // Request Status Chart
        const requestCtx = document.getElementById('requestChart').getContext('2d');
        new Chart(requestCtx, {
            type: 'pie',
            data: {
                labels: ['Pending', 'Accepted', 'Rejected', 'Completed'],
                datasets: [{
                    label: 'Request Distribution',
                    data: [
                        ${requestStats['Pending'] != null ? requestStats['Pending'] : 0},
                        ${requestStats['Accepted'] != null ? requestStats['Accepted'] : 0},
                        ${requestStats['Rejected'] != null ? requestStats['Rejected'] : 0},
                        ${requestStats['Completed'] != null ? requestStats['Completed'] : 0}
                    ],
                    backgroundColor: ['#ffd700', '#28a745', '#dc3545', '#17a2b8']
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: { position: 'top' },
                    title: { display: true, text: 'Request Distribution' }
                }
            }
        });

        // Ẩn spinner sau khi tải
        document.querySelector('.loading-spinner').style.display = 'none';
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Fallback cho Chart.js
        if (!window.Chart) {
            document.write('<script src="/js/chart.min.js"><\/script>');
        }
    </script>
</body>
</html>