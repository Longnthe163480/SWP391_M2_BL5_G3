<%-- 
    Document   : EditPost
    Created on : Apr 27, 2025, 10:44:03 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Post</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .form-container {
                max-width: 600px;
                margin: 80px auto;
                background: #fff;
                padding: 2rem;
                border-radius: 10px;
                box-shadow: 0 2px 16px rgba(0,0,0,0.08);
            }
        </style>
    </head>
    <body>
        <jsp:include page="guest/Header.jsp"></jsp:include>
        <div class="container">
            <div class="form-container">
                <h2 class="mb-4">Edit Post</h2>
                <form action="EditPost" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${post.id}" />
                    <div class="mb-3">
                        <label for="title" class="form-label">Title</label>
                        <input type="text" class="form-control" id="title" name="title" value="${post.title}" required maxlength="200">
                    </div>
                    <div class="mb-3">
                        <label for="content" class="form-label">Content</label>
                        <textarea class="form-control" id="content" name="content" rows="8" required>${post.content}</textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Current Image</label><br>
                        <c:choose>
                            <c:when test="${not empty postImage}">
                                <img src="${postImage.imageUrl}" alt="Current Image" style="max-width: 100%; height: auto; margin-bottom: 10px;" />
                            </c:when>
                            <c:otherwise>
                                <span class="text-muted">No image uploaded.</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="mb-3">
                        <label for="image" class="form-label">Upload New Image</label>
                        <input type="file" class="form-control" id="image" name="image" accept="image/*">
                        <div class="form-text">Leave blank to keep current image.</div>
                    </div>
                    <div class="d-flex justify-content-between">
                        <a href="ViewAllPost" class="btn btn-secondary">Cancel</a>
                        <button type="submit" class="btn btn-primary">Update Post</button>
                    </div>
                </form>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger mt-3">${error}</div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="alert alert-success mt-3">${success}</div>
                </c:if>
            </div>
        </div>
        <jsp:include page="guest/Footer.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
