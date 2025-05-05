<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Job Management</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="guest/Header.jsp" />
<div class="container mt-5">
    <c:if test="${sessionScope.account.roleid==3}">
        <h2 class="mb-4">Job</h2>
        <!-- Search Form -->
        <form action="JobManager" method="get" class="mb-4 d-flex">
            <input type="text" name="search" class="form-control me-2" placeholder="Search job..." value="${search}">
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
        <!-- Add Job Form -->
        <form action="JobManager" method="post" class="mb-4 d-flex">
            <input type="text" name="jobName" class="form-control me-2" placeholder="Enter new job name" required>
            <button type="submit" name="action" value="add" class="btn btn-success">Add Job</button>
        </form>

        <!-- Job List Table -->
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th>Job Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="job" items="${requestScope.jobList}" varStatus="loop">
                <tr>
                    <td>${(currentPage-1)*5 + loop.index + 1}</td>
                    <td>
                        <c:if test="${param.editId == job.id}">
                            <form action="JobManager" method="post" class="d-flex">
                                <input type="hidden" name="jobId" value="${job.id}">
                                <input type="text" name="jobName" value="${job.jobname}" class="form-control me-2" required>
                                <button type="submit" name="action" value="update" class="btn btn-primary btn-sm me-2">Save</button>
                                <a href="JobManager?page=${currentPage}&search=${search}" class="btn btn-secondary btn-sm">Cancel</a>
                            </form>
                        </c:if>
                        <c:if test="${param.editId != job.id}">
                            ${job.jobname}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${param.editId != job.id}">
                            <a href="JobManager?editId=${job.id}&page=${currentPage}&search=${search}" class="btn btn-warning btn-sm me-2">Edit</a>
                            <form action="JobManager?page=${currentPage}&search=${search}" method="post" style="display:inline;">
                                <input type="hidden" name="jobId" value="${job.id}">
                                <button type="submit" name="action" value="delete" class="btn btn-danger btn-sm"
                                        onclick="return confirm('Are you sure you want to delete this job?');">Delete</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!-- Pagination -->
        <nav>
            <ul class="pagination justify-content-center">
                <c:forEach begin="1" end="${totalPage}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="JobManager?page=${i}&search=${search}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </c:if>
    <c:if test="${sessionScope.account.roleid!=3}">
        <div class="alert alert-danger mt-5">You do not have permission to access this page.</div>
    </c:if>
</div>
<jsp:include page="guest/Footer.jsp" />
</body>
</html> 