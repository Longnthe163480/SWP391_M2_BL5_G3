<%-- 
    Document   : RegisterMentorRequest
    Created on : Apr 28, 2025, 10:50:56 PM
    Author     : legen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request Register Mentor</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="guest/Header.jsp" />
<div class="container-fluid d-flex" style="min-height: 80vh; margin-top: 70px;">
    
    <div class="flex-grow-1 p-4">
        <h2>Request Register Mentor</h2>
        <table class="table table-bordered table-hover mt-4">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Account Name</th>
                    <th>Email</th>
                    <th>Current Role</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="mentee" items="${menteeList}">
                    <tr>
                        <td>${mentee.id}</td>
                        <td>${mentee.accountname}</td>
                        <td>${mentee.email}</td>
                        <td>Mentee</td>
                        <td>
                            <form action="ApproveMentorController" method="post" style="display:inline;">
                                <input type="hidden" name="accountId" value="${mentee.id}" />
                                <button type="submit" class="btn btn-primary btn-sm">Approve Mentor</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="guest/Footer.jsp" />
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
