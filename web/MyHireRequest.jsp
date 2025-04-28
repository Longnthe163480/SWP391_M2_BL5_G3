<%-- 
    Document   : MyHireRequest
    Created on : Apr 25, 2025, 11:10:10 AM
    Author     : legen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Hire Request List</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,700,700i&display=swap" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">

        <!-- =======================================================
        * Template Name: Moderna - v4.11.0
        * Template URL: https://bootstrapmade.com/free-bootstrap-template-corporate-moderna/
        * Author: BootstrapMade.com
        * License: https://bootstrapmade.com/license/
        ======================================================== -->
    </head>
    <body>
        <c:if test="${sessionScope.account.roleid==1}">
            <jsp:include page="guest/Header.jsp" />
            <main id="main" style="position: relative">
                <section class="breadcrumbs">
                    <div class="container">
                        <div class="d-flex justify-content-between align-items-center">
                            <h2>Hire Request List</h2>
                            <ol>
                                <li><a href="ViewTop3Mentor">Home</a></li>
                            </ol>
                        </div>
                    </div>
                </section>
                <div class="d-flex">
                    <form action="SearchHireRequest?menteeid=${sessionScope.getmentee.id}" method="post">
                        <div class="input-group" style="margin: 10px; padding-right: 500px;padding-left: 100px;">
                            <input value="${searchtext}" type="text" class="form-control" placeholder="Search" name="name">
                            <button type="submit" class="btn btn-secondary"><i class="bi-search"></i></button>  
                        </div> 
                    </form>
                    <form action="CreateHireRequest.jsp" method="post">
                        <button type="submit" class="btn btn-outline-primary btn-lg" style="margin-top: 10px;margin-right: 50px;font-size: 15px;float: right; width: 300px;height: 50px;">Add new Hire Request</button>
                    </form>
                </div>
                <div class="container-fluid h-custom">
                    <div class="row d-flex justify-content-start align-items-center h-100">
                        <table class="table table-striped table-hover" style="margin: 50px; width:93%">
                            <tr>
                                <th>ID</th>
                                <th>Mentor Name</th>
                                <th>Title</th>
                                <th>Content</th>
                                <th>Cost Hire</th>
                                <th>Status</th>
                                <th>Edit</th>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach items="${hirerequest}" var="c">
                                <tr>
                                    <td>${i}</td>
                                    <td>${c.mentorname}</td>
                                    <td>${c.title}</td>
                                    <td>${c.content}</td>
                                    <td>${c.costhire}</td>                                      
                                    <td>${c.status}</td> 
                                    <td>
                                        <form action="EditHireRequest?hirereid=${c.id}" method="post">
                                            <button type="submit" class="btn btn-primary">Edit</button>
                                        </form>
                                    </td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute; margin-left: 20px">Back</button>
                <nav aria-label="Page navigation example">
                    <ul class="pagination" style="display: flex;justify-content: center;align-items: center;">
                        <c:if test="${tag>1}">
                            <li class="page-item">
                                <a class="page-link" href="ViewAllHireRequest?index=${tag-1}&menteeid=${sessionScope.getmentee.id}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                        </c:if>               
                        <c:forEach begin="1" end="${endpage}" var="i">
                            <li class="page-item"><a class="page-link" href="ViewAllHireRequest?index=${i}&menteeid=${sessionScope.getmentee.id}">${i}</a></li>
                        </c:forEach>
                        <c:if test="${tag<endpage}">
                            <li class="page-item">
                                <a class="page-link" href="ViewAllHireRequest?index=${tag+1}&menteeid=${sessionScope.getmentee.id}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
                <jsp:include page="guest/Footer.jsp" />
            </main>
        </c:if>

        <c:if test="${sessionScope.account.roleid==3}">
            <jsp:include page="guest/Header.jsp" />
            <main id="main" style="position: relative">
                <section class="breadcrumbs">
                    <div class="container">
                        <div class="d-flex justify-content-between align-items-center">
                            <h2>All Hire Requests</h2>
                            <ol>
                                <li><a href="AdminDashboard.jsp">Dashboard</a></li>
                            </ol>
                        </div>
                    </div>
                </section>
                <div class="container-fluid h-custom">
                    <div class="row d-flex justify-content-start align-items-center h-100">
                        <table class="table table-striped table-hover" style="margin: 50px; width:93%">
                            <tr>
                                <th>ID</th>
                                <th>Mentor Name</th>
                                <th>Title</th>
                                <th>Content</th>
                                <th>Cost Hire</th>
                                <th>Status</th>
                            </tr>
                            <c:forEach items="${hirerequest}" var="c">
                                <tr>
                                    <td>${c.id}</td>
                                    <td>${c.mentorname}</td>
                                    <td>${c.title}</td>
                                    <td>${c.content}</td>
                                    <td>${c.costhire}</td>
                                    <td>${c.status}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute; margin-left: 20px">Back</button>
                <nav aria-label="Page navigation example">
                    <ul class="pagination" style="display: flex;justify-content: center;align-items: center;">
                        <c:if test="${tag>1}">
                            <li class="page-item">
                                <a class="page-link" href="ViewAllHireRequest?index=${tag-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                        </c:if>               
                        <c:forEach begin="1" end="${endpage}" var="i">
                            <li class="page-item"><a class="page-link" href="ViewAllHireRequest?index=${i}">${i}</a></li>
                        </c:forEach>
                        <c:if test="${tag<endpage}">
                            <li class="page-item">
                                <a class="page-link" href="ViewAllHireRequest?index=${tag+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
                <jsp:include page="guest/Footer.jsp" />
            </main>
        </c:if>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="assets/vendor/waypoints/noframework.waypoints.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>
    </body>
</html>
