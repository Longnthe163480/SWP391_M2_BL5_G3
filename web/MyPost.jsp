<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Posts</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
        <style>
            .post-card {
                transition: transform 0.2s;
            }
            .post-card:hover {
                transform: translateY(-5px);
            }
            .post-meta {
                font-size: 0.9rem;
                color: #6c757d;
            }
            .post-excerpt {
                display: -webkit-box;
                -webkit-line-clamp: 3;
                -webkit-box-orient: vertical;
                overflow: hidden;
            }
            .action-buttons {
                display: flex;
                gap: 0.5rem;
            }
        </style>
    </head>
    <body>
        <jsp:include page="guest/Header.jsp"></jsp:include>
        <div class="container mt-4 mb-5">
            <!-- Header -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <div>
                    <h1>My Posts</h1>
                </div>
                <a href="CreatePost" class="btn btn-primary">
                    <i class="bi bi-plus-lg"></i> Create New Post
                </a>
            </div>

            <!-- Posts List -->
            <div class="row">
                <c:forEach var="post" items="${posts}">
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="card h-100 post-card">
                            <div class="card-body">
                                <h5 class="card-title">${post.title}</h5>
                                <div class="post-meta mb-2">
                                    <i class="bi bi-calendar"></i> ${post.createdDate}
                                    <br>
                                    
                                </div>
                                <p class="card-text post-excerpt">${post.content}</p>
                            </div>
                            <div class="card-footer bg-transparent">
                                <div class="action-buttons">
                                    <a href="ViewPostDetail?id=${post.id}" class="btn btn-outline-primary btn-sm">
                                        <i class="bi bi-eye"></i> View
                                    </a>
                                    <a href="EditPost?id=${post.id}" class="btn btn-outline-secondary btn-sm">
                                        <i class="bi bi-pencil"></i> Edit
                                    </a>
                                    <a href="DeletePost?id=${post.id}" class="btn btn-outline-danger btn-sm" 
                                       onclick="return confirm('Are you sure you want to delete this post?')">
                                        <i class="bi bi-trash"></i> Delete
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Pagination -->
            <c:if test="${endpage >= 1}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination" style="display: flex;justify-content: center;align-items: center;">
                        <c:if test="${tag>1}">
                            <li class="page-item">
                                <a class="page-link" href="MyPost?index=${tag-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${endpage}" var="i">
                            <li class="page-item ${tag==i ? 'active' : ''}">
                                <a class="page-link" href="MyPost?index=${i}">${i}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${tag<endpage}">
                            <li class="page-item">
                                <a class="page-link" href="MyPost?index=${tag+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </c:if>
            
            <!-- No posts message -->
            <c:if test="${empty posts}">
                <div class="alert alert-info text-center">
                    You haven't created any posts yet.
                    <br>
                    <a href="CreatePost" class="btn btn-primary mt-3">
                        <i class="bi bi-plus-lg"></i> Create Your First Post
                    </a>
                </div>
            </c:if>
        </div>
        <jsp:include page="guest/Footer.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html> 