<%-- 
    Document   : MentorProfile
    Created on : Mar 22, 2023, 3:49:24 AM
    Author     : okanh
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Mentee Profile</title>
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
        <c:if test="${sessionScope.account.roleid==2}">
        <jsp:include page="/guest/Header.jsp" />

            <main id="main" style="position: relative">
                <!-- ======= Our Portfolio Section ======= -->
                <section class="breadcrumbs">
                    <div class="container">

                        <div class="d-flex justify-content-between align-items-center">
                            <h2>Mentor Profile</h2>
                        </div>

                    </div>
                </section><!-- End Our Portfolio Section -->

                <!-- ======= Portfolio Details Section ======= -->
                <section id="portfolio-details" class="portfolio-details">
                    <div class="container">

                        <div class="row gy-4">
                                        <p class="text-success my-4 fw-bold">
                                        ${mess}
                                    </p>
                            <div class="col-lg-8">
                                <div class="portfolio-details-slider swiper">
                                    <div class="swiper-wrapper align-items-center">

                                        <div class="swiper-slide">
                                            <img src="assets/img/mentor/${getmentor.avatar}" alt="">
                                    </div>
                                </div>
                                <div class="swiper-pagination"></div>
                            </div>
                        </div>

                        <div class="col-lg-4">
                            <div class="portfolio-info">
                                <h3>${getmentor.name}</h3>
                                <ul>
                                    <li><strong>Sex</strong>: 
                                        <c:if test="${getmentor.sex.equals('M')}">Male</c:if>
                                        <c:if test="${getmentor.sex.equals('F')}">Female</c:if>
                                    </li>
                                    <li><strong>Address</strong>: ${getmentor.address}</li>
                                    <li><strong>Phone</strong>: ${getmentor.phone}</li>
                                    <li><strong>Birthday</strong>: ${getmentor.birthday}</li>
                                    <li><strong>Introduce</strong>: ${getmentor.introduce}</li>
                                    <li><strong>Achievement</strong>: ${getmentor.achievement}</li>
                                    <li><strong>Cost Hire</strong>: ${getmentor.cost}</li>
                                </ul>
                            </div>
                                 <div class="portfolio-description">
                                <h2>Edit Profile </h2>
                                <div>
                                    <button class="btn btn-primary">
                                        <a href="EditMentorProfile.jsp">Edit</a>
                                        
                                    </button>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </section><!-- End Portfolio Details Section -->
            <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute;bottom: 20px;left: 20px;">Back</button>

        </main><!-- End #main -->
        <jsp:include page="/guest/Footer.jsp" />

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
        </c:if>
    </body>
</html>



