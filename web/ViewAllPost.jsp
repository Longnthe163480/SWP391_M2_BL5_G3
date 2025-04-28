<%-- 
    Document   : ViewAllPost
    Created on : Apr 27, 2025, 10:43:32 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Posts</title>
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
        </style>
    </head>
    <body>
        <jsp:include page="guest/Header.jsp"></jsp:include>
        <div class="container mt-4 mb-5">
            <!-- Header -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h1>All Posts</h1>
                <c:if test="${sessionScope.account != null}">
                    <a href="CreatePost" class="btn btn-primary">
                        <i class="bi bi-plus-lg"></i> Create New Post
                    </a>
                </c:if>
            </div>

            <!-- Search Form + Create Post Button -->
            <div class="d-flex mb-4 align-items-center">
                <form action="SearchPost" method="GET" class="flex-grow-1 me-2">
                    <div class="input-group">
                        <input type="text" name="keyword" class="form-control" placeholder="Search posts...">
                        <button class="btn btn-outline-secondary" type="submit">
                            <i class="bi bi-search"></i>
                        </button>
                    </div>
                </form>
                <c:if test="${sessionScope.account != null}">
                    <a href="CreatePost" class="btn btn-primary ms-2">
                        <i class="bi bi-plus-lg"></i> Create Post
                    </a>
                </c:if>
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
                                </div>
                                <p class="card-text post-excerpt">${post.content}</p>
                            </div>
                            <div class="card-footer bg-transparent">
                                <a href="ViewPostDetail?id=${post.id}" class="btn btn-outline-primary btn-sm">
                                    Read More
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Pagination -->
            <c:if test="${totalPages > 1}">
                <nav aria-label="Page navigation" class="mt-4">
                    <div class="d-flex justify-content-between align-items-center">
                        <ul class="pagination mb-0">
                            <!-- Previous button -->
                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                <a class="page-link" href="ViewAllPost?page=${currentPage - 1}" ${currentPage == 1 ? 'tabindex="-1" aria-disabled="true"' : ''}>
                                    <i class="bi bi-chevron-left"></i> Previous
                                </a>
                            </li>
                            
                            <!-- Page numbers -->
                            <c:forEach begin="1" end="${totalPages}" var="i">
                                <li class="page-item ${currentPage == i ? 'active' : ''}">
                                    <a class="page-link" href="ViewAllPost?page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            
                            <!-- Next button -->
                            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                <a class="page-link" href="ViewAllPost?page=${currentPage + 1}" ${currentPage == totalPages ? 'tabindex="-1" aria-disabled="true"' : ''}>
                                    Next <i class="bi bi-chevron-right"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </c:if>
            
            <!-- No posts message -->
            <c:if test="${empty posts}">
                <div class="alert alert-info text-center">
                    No posts found.
                </div>
            </c:if>
        </div>
        <jsp:include page="guest/Footer.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
