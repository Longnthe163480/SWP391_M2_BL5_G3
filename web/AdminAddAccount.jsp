<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Add Account - Admin</title>
    <link
      href="assets/vendor/bootstrap/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <style>
      .form-container {
        max-width: 500px;
        margin: 0 auto;
        background: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.08);
      }
    </style>
  </head>
  <body>
    <jsp:include page="guest/Header.jsp" />
    <div class="container mt-5">
      <h2 class="mb-4">Add Account</h2>
      <c:if test="${error != null}">
        <div class="alert alert-danger">${error}</div>
      </c:if>
      <form action="AdminAddAccount" method="post" class="form-container">
        <div class="mb-3">
          <label class="form-label"
            >Username <span class="text-danger">*</span></label
          >
          <input
            name="accountname"
            type="text"
            class="form-control"
            required
            pattern="[A-Za-z0-9_]+"
            title="Username must contain only letters, numbers and underscores"
          />
        </div>
        <div class="mb-3">
          <label class="form-label"
            >Email <span class="text-danger">*</span></label
          >
          <input
            name="email"
            type="email"
            class="form-control"
            required
            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
            title="Please enter a valid email address"
          />
        </div>
        <div class="mb-3">
          <label class="form-label"
            >Password <span class="text-danger">*</span></label
          >
          <input
            name="password"
            type="password"
            class="form-control"
            required
            minlength="6"
            title="Password must be at least 6 characters"
          />
        </div>
        <div class="mb-3">
          <label class="form-label"
            >Role <span class="text-danger">*</span></label
          >
          <select name="roleid" class="form-select" required>
            <option value="">Select a role</option>
            <c:forEach var="role" items="${roleList}">
              <option value="${role.id}">${role.name}</option>
            </c:forEach>
          </select>
        </div>
        <div class="mt-4">
          <button type="submit" class="btn btn-success">Add Account</button>
          <a href="ViewAllAccount" class="btn btn-secondary">Cancel</a>
        </div>
      </form>
    </div>
    <jsp:include page="guest/Footer.jsp" />
  </body>
</html>
