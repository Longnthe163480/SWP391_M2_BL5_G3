<%-- 
    Document   : MentorList
    Created on : Apr 19, 2025, 12:44:28 PM
    Author     : legen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "entity.*" %>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Mentor List</title>
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
        <!-- ======= Header ======= -->
        <jsp:include page="Header.jsp"></jsp:include>
            <!-- End Header -->

            <main id="main">
                <!-- ======= Our Team Section ======= -->
                <section class="breadcrumbs">
                    <div class="container">

                        <div class="d-flex justify-content-between align-items-center">
                            <h2>Mentor List</h2>
                            <ol>
                                <li><a href="ViewTop3Mentor">Home</a></li>
                            </ol>
                        </div>

                    </div>
                </section><!-- End Our Team Section -->
                <form class="d-flex" action="SearchMentor" method="post">
                    <div class="input-group" style="margin: 10px; padding-right: 500px;padding-left:  100px; ">
                        <input value="${searchtext}" type="text" class="form-control" placeholder="Search" name="name">
                    <button type="submit" class="btn btn-secondary" ><i class="bi-search"></i></button>
                </div>
            </form>
            <!-- ======= Team Section ======= -->
            <section class="team" data-aos="fade-up" data-aos-easing="ease-in-out" data-aos-duration="500">
                <div class="container">

                    <div class="row">
                        <c:forEach items="${allMentor}" var="m">
                            <div class="col-lg-4 col-md-6 d-flex align-items-stretch">
                                <div class="member">
                                    <div class="member-img">
                                        <a href="ViewMentorDetail?mentorid=${m.id}"><img src="assets/img/mentor/${m.avatar}" class="img-fluid" alt=""></a>
                                        <div class="social">
                                            <a href=""><i class="bi bi-twitter"></i></a>
                                            <a href=""><i class="bi bi-facebook"></i></a>
                                            <a href=""><i class="bi bi-instagram"></i></a>
                                            <a href=""><i class="bi bi-linkedin"></i></a>
                                        </div>
                                    </div>
                                    <div class="member-info">
                                        <h4>${m.name}</h4>
                                        <span>Developer, Tester</span>
                                        <p>${m.introduce}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section><!-- End Team Section -->


        </main><!-- End #main -->
        <nav aria-label="Page navigation example" style="position: relative">
            <ul class="pagination" style="display: flex;justify-content: center;align-items: center;  ">
                <c:if test="${tag>1}">
                    <li class="page-item">
                    <a class="page-link" href="ViewAllMentor?index=${tag-1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>
                </c:if>
                
                <c:forEach begin="1" end="${endpage}" var="i">
                    <li class="page-item"><a class="page-link" href="ViewAllMentor?index=${i}">${i}</a></li>
                </c:forEach>
                    
                <c:if test="${tag<enpage}">
                <li class="page-item">
                    <a class="page-link" href="ViewAllMentor?index=${tag+1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
                </c:if>
                <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute;bottom: 20px;left: 20px;">Back</button>
            </ul>
        </nav>
        <!-- ======= Footer ======= -->
        <jsp:include page="Footer.jsp"></jsp:include>
        <!-- End Footer -->

        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

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
