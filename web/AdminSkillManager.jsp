<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Skill Management</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="guest/Header.jsp" />
<div class="container mt-5">
    <c:if test="${sessionScope.account.roleid==3}">
        <h2 class="mb-4">Skill</h2>
        <!-- Search Form -->
        <form action="AdminSkillManager" method="get" class="mb-4 d-flex">
            <input type="text" name="search" class="form-control me-2" placeholder="Search skill..." value="${search}">
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
        <!-- Add Skill Form -->
        <form action="AdminSkillManager" method="post" class="mb-4 d-flex">
            <input type="text" name="skillName" class="form-control me-2" placeholder="Enter new skill name" required>
            <button type="submit" name="action" value="add" class="btn btn-success">Add Skill</button>
        </form>

        <!-- Skill List Table -->
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th>Skill Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="skill" items="${requestScope.skillList}" varStatus="loop">
                <tr>
                    <td>${(currentPage-1)*5 + loop.index + 1}</td>
                    <td>
                        <c:if test="${param.editId == skill.id}">
                            <form action="AdminSkillManager" method="post" class="d-flex">
                                <input type="hidden" name="skillId" value="${skill.id}">
                                <input type="text" name="skillName" value="${skill.name}" class="form-control me-2" required>
                                <button type="submit" name="action" value="update" class="btn btn-primary btn-sm me-2">Save</button>
                                <a href="AdminSkillManager?page=${currentPage}&search=${search}" class="btn btn-secondary btn-sm">Cancel</a>
                            </form>
                        </c:if>
                        <c:if test="${param.editId != skill.id}">
                            ${skill.name}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${param.editId != skill.id}">
                            <a href="AdminSkillManager?editId=${skill.id}&page=${currentPage}&search=${search}" class="btn btn-warning btn-sm me-2">Edit</a>
                            <form action="AdminSkillManager?page=${currentPage}&search=${search}" method="post" style="display:inline;">
                                <input type="hidden" name="skillId" value="${skill.id}">
                                <button type="submit" name="action" value="delete" class="btn btn-danger btn-sm"
                                        onclick="return confirm('Are you sure you want to delete this skill?');">Delete</button>
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
                        <a class="page-link" href="AdminSkillManager?page=${i}&search=${search}">${i}</a>
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