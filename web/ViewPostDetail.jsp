<%-- 
    Document   : ViewPostDetail
    Created on : Apr 27, 2025, 10:43:40 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${post.title}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                padding-top: 60px; /* Thêm padding-top để tránh bị Header che */
            }
            .post-content {
                white-space: pre-line;
                padding: 12px 18px;
                font-size: 1.1rem;
                line-height: 1.7;
                text-align: left;
                background: #fafbfc;
                border-radius: 6px;
                margin-bottom: 2rem;
            }
            .comment-section {
                margin-top: 2rem;
            }
        </style>
    </head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${post.title}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                padding-top: 60px; /* Thêm padding-top để tránh bị Header che */
            }
            .post-content {
                white-space: pre-line;
                padding: 12px 18px;
                font-size: 1.1rem;
                line-height: 1.7;
                text-align: left;
                background: #fafbfc;
                border-radius: 6px;
                margin-bottom: 2rem;
            }
            .comment-section {
                margin-top: 2rem;
            }
        </style>
    </head>
    <body>
        <jsp:include page="guest/Header.jsp"></jsp:include>
        <div class="container mt-4">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <!-- Post Title -->
                    <h1 class="mb-4">${post.title}</h1>
                    
                    <!-- Post Meta -->
                    <div class="text-muted mb-4 d-flex align-items-center">
                        <small>
                            Posted on ${post.createdDate}
                            <span class="ms-2">| By <b>${accountNameMap[post.accountId]}</b></span>
                        </small>
                        <span class="ms-3">
                            <i class="bi bi-heart"></i> ${likeCount} likes
                        </span>
                        <c:if test="${sessionScope.account != null}">
                            <form action="LikePost" method="post" style="display:inline; margin-left: 16px;">
                                <input type="hidden" name="postId" value="${post.id}" />
                                <button type="submit" class="btn btn-link p-0" style="font-size:1.1rem;">
                                    <c:choose>
                                        <c:when test="${hasLiked}">
                                            <i class="bi bi-heart-fill text-danger"></i> Unlike
                                        </c:when>
                                        <c:otherwise>
                                            <i class="bi bi-heart"></i> Like
                                        </c:otherwise>
                                    </c:choose>
                                </button>
                            </form>
                            <c:if test="${sessionScope.account.id == post.accountId}">
                                <a href="EditPost?id=${post.id}" class="btn btn-outline-secondary btn-sm ms-3">Edit Post</a>
                                <form action="DeletePost" method="post" style="display:inline; margin-left: 8px;">
                                    <input type="hidden" name="id" value="${post.id}" />
                                    <button type="submit" class="btn btn-outline-danger btn-sm" onclick="return confirm('Are you sure you want to delete this post?');">Delete Post</button>
                                </form>
                            </c:if>
                        </c:if>
                    </div>
                    
                    <!-- Post Content -->
                    <div class="post-content mb-4">
                        ${post.content}
                    </div>
                    
                    <!-- Post Images -->
                    <c:if test="${not empty postImages}">
                        <div class="post-images mb-4">
                            <div class="row">
                                <c:forEach var="image" items="${postImages}">
                                    <div class="col-md-4 mb-3">
                                        <img src="${image.imageUrl}" class="img-fluid rounded" alt="Post image">
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    
                    <!-- Comments Section -->
                    <div class="comment-section">
                        <h3>Comments</h3>
                        
                        <c:if test="${empty comments}">
                            <p class="text-muted">No comments yet.</p>
                        </c:if>
                        
                        <c:forEach var="comment" items="${comments}">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <p class="card-text">${comment.content}</p>
                                    <small class="text-muted">
                                        By ${accountNameMap[comment.accountId]} on ${comment.createdDate}
                                        <c:if test="${sessionScope.account != null && sessionScope.account.id == comment.accountId}">
                                            <form action="DeleteComment" method="post" style="display:inline; margin-left: 8px;">
                                                <input type="hidden" name="commentId" value="${comment.id}" />
                                                <input type="hidden" name="postId" value="${post.id}" />
                                                <button type="submit" class="btn btn-outline-danger btn-sm" onclick="return confirm('Are you sure you want to delete this comment?');">Delete</button>
                                            </form>
                                        </c:if>
                                    </small>
                                </div>
                            </div>
                        </c:forEach>
                        
                        <c:if test="${sessionScope.account != null}">
                            <!-- Comment Form -->
                            <form action="CreateComment" method="POST" class="mt-4">
                                <input type="hidden" name="postId" value="${post.id}">
                                <div class="mb-3">
                                    <textarea class="form-control" name="content" rows="3" 
                                              placeholder="Write a comment..." required></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary">Post Comment</button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.account == null}">
                            <div class="alert alert-info mt-4">
                                Bạn cần <a href="Login.jsp">đăng nhập</a> để bình luận và bày tỏ cảm xúc.
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        
        <jsp:include page="guest/Footer.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
