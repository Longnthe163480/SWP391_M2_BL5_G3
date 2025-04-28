<%-- 
    Document   : MentorInformation
    Created on : Apr 20, 2025, 9:42:11 PM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>More Information</title>
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
        <jsp:include page="Header.jsp"></jsp:include>
            <main id="main" style="position: relative">

                <!-- ======= About Us Section ======= -->
                <section class="breadcrumbs">
                    <div class="container">

                        <div class="d-flex justify-content-between align-items-center">
                            <h2>Mentor Detail</h2>
                            <ol>
                                <li><a href="ViewTop3Mentor">Home</a></li>
                            </ol>
                        </div>

                    </div>
                </section><!-- End About Us Section -->

                <!-- ======= About Section ======= -->
                <section class="about" data-aos="fade-up">
                    <div class="container">

                        <div class="row">
                            <div class="col-lg-6">
                                <img src="assets/img/mentor/${sessionScope.Mentor.avatar}" class="img-fluid" alt="">
                        </div>
                        <div class="col-lg-6 pt-4 pt-lg-0">
                            <h3>${sessionScope.Mentor.name}</h3>
                            <p class="fst-italic">
                                ${sessionScope.Mentor.introduce}
                            </p><br>
                            <p><strong>Profession:</strong></p>
                            <ul>
                                <c:forEach items="${job}" var="j">
                                    <li><i class="bi bi-check2-circle"></i>${j.jobname}</li>
                                    </c:forEach>

                            </ul>
                            <p><strong>
                                    Here is some information about me. I think you'll want to see them.
                                </strong>
                            </p>
                        </div>
                    </div>

                </div>
            </section><!-- End About Section -->

            <!-- ======= Our Skills Section ======= -->
            <section class="skills" data-aos="fade-up">
                <div class="container">

                    <div class="section-title">
                        <h2>Our Skills</h2>
                        <p>These are my skills. These skills were cultivated and developed by me during my time at school. These skills help me in finding work and making money.</p>
                    </div>

                    <div class="skills-content">
                        <c:forEach items="${skill}" var="s">
                            <div class="progress">
                                <div class="progress-bar bg-success" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100">
                                    <span class="skill">${s.name} <i class="val"></i></span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
            </section><!-- End Our Skills Section -->
            <% float rate= (float) request.getAttribute("star");
               float x=(float) Math.round(rate * 10) / 10;
            %> 
            <!-- ======= Tetstimonials Section ======= -->
            <section class="testimonials" data-aos="fade-up">
                <div class="container">

                    <div class="section-title">
                        <h2>Avage Star</h2>
                        <p><strong>Average Star: </strong><%= x%></p>
                        <% for(int i=1;i<x;i++){ %>
                        <span id="boot-icon" class="bi bi-star" style="font-size: 56px; color: rgb(255, 210, 48);"></span>
                        <%}%>
                    </div>

                    <div class="section-title">
                        <h2>Feed back</h2>
                        <div class="testimonials-carousel swiper">
                            <div class="swiper-wrapper">
                                <c:forEach items="${feedback}" var="f">
                                    <div class="testimonial-item swiper-slide">
                                    <img src="assets/img/mentor/${f.avatar}" class="testimonial-img" alt="">
                                    <h3>${f.name}</h3>                                   
                                    <h4>Good Student</h4>
                                    <p>
                                        <i class="bx bxs-quote-alt-left quote-icon-left"></i>
                                        ${f.content}
                                        <i class="bx bxs-quote-alt-right quote-icon-right"></i>
                                    </p>
                                </div>
                                </c:forEach>                                                                                                                                                      
                            </div>
                            <div class="swiper-pagination"></div>
                        </div>

                    </div>
                </div>
            </section><!-- End Ttstimonials Section -->
            <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute;bottom: 20px;left: 20px;">Back</button>

        </main><!-- End #main -->
        <jsp:include page="Footer.jsp"></jsp:include>

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
