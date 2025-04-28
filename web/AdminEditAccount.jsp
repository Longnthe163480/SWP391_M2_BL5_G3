<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Account - Admin</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="guest/Header.jsp" />
<div class="container mt-5">
    <h2 class="mb-4">Edit Account</h2>
    <c:if test="${error != null}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form action="AdminEditAccount" method="post" class="mb-4" style="max-width: 500px;">
        <input type="hidden" name="id" value="${account.id}">
        <div class="mb-3">
            <label class="form-label">Username</label>
            <input name="accountname" type="text" class="form-control" value="${account.accountname}" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Email</label>
            <input name="email" type="email" class="form-control" value="${account.email}" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Role</label>
            <select name="roleid" class="form-select" required>
                <c:forEach var="role" items="${roleList}">
                    <option value="${role.id}" <c:if test="${role.id == account.roleid}">selected</c:if>>${role.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">New Password (leave blank to keep current)</label>
            <input name="password" type="password" class="form-control" autocomplete="new-password">
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="AccountList.jsp" class="btn btn-secondary">Cancel</a>
    </form>
</div>
<jsp:include page="guest/Footer.jsp" />
</body>
</html> 