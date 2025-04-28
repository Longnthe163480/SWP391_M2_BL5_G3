<%-- 
    Document   : EditMentorProfile
    Created on : Mar 22, 2023, 4:04:38 AM
    Author     : okanh
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Edit</title>
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
        <style>
            .cr {
                display: flex;
                justify-content: space-between;
                width: 100%;
                margin: 20px;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.account.roleid==2}">
            <jsp:include page="guest/Header.jsp" />
            <<main id="main">
            <section class="breadcrumbs">
                <div class="container">

                    <div class="d-flex justify-content-between align-items-center">
                        <h2>Edit Mentor Profile </h2>
                        <ol>
                            <li><a href="ViewTop3Mentor">Home</a></li>
                        </ol>
                    </div>

                </div>
            </section>
            <c:if test="${done !=null}">
                <p class="text-success" style="text-align: center;margin: 10px">
                    ${done}
                </p>
            </c:if>
            <c:if test="${error !=null}">
                <p class="text-danger"style="text-align: center;margin: 10px">
                    ${error}
                </p>
            </c:if>
            <form class="" action="EditMentorProfile?accountid=${sessionScope.account.id}&mentorid=${sessionScope.getmentor.id}" method="post">
                    <div class="cr" >
                <!--Login part--> 
                    <div class="" style="height: 900px;  flex: 1.5;">

                        <h2 class="text-center text-primary fw-bold">Edit Profile</h2>


                        <!--Username input--> 
                        <div class="mb-3">
                            <label class="form-label">Name</label>
                            <input name="name" type="text" class="form-control form-control-lg" value="${sessionScope.getmentor.name}" required>   
                        </div>
                        <div class="mb-3">
                            <label class="form-label" >Sex</label><br>
                            <input type="radio" name="sex" value="M" required/>Male
                            <input type="radio" name="sex" value="F" />Female
                        </div>
                        <!--Password input--> 
                        <div class="mb-3">
                            <label class="form-label">Address</label>
                            <input name="address" type="text" class="form-control form-control-lg" value="${sessionScope.getmentor.address}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" >Email</label>
                            <input name="email" type="text" class="form-control form-control-lg" value="${sessionScope.account.email}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Introduce</label>
                            <textarea name="introduce" type="text" class="form-control form-control-lg" required>${sessionScope.getmentor.introduce}</textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Achievement</label>
                            <textarea name="achievment" rows="4" class="form-control form-control-lg" required>${sessionScope.getmentor.achievement}</textarea>
                        </div>
                        <div class="text-center text-lg-start mt-4 pt-2 mb-4" style="position: relative">
                            <button type="submit" class="btn btn-outline-primary btn-lg"style="padding-left: 2.5rem; padding-right: 2.5rem;">
                                Save
                            </button>
                            <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute; margin-left: 20px">Back</button>
                        </div>   
                    </div>
                    <div class="" style="height: 600px;  flex: 1.5; margin-left: 50px; margin-right: 50px">
                        <section class="testimonials" data-aos="fade-up">
                            <div class="container">
                                <div class="testimonials-carousel swiper">  
                                    <div class="testimonial-item swiper-slide">
                                        <img src="assets/img/mentor/${sessionScope.getmentor.avatar}" class="testimonial-img" alt="">                   
                                    </div>                                              
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Phone</label>
                                    <input name="phone" type="text" class="form-control form-control-lg" value="${sessionScope.getmentor.phone}" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Birthday</label>
                                    <input name="birthday" type="date" class="form-control form-control-lg" value="${sessionScope.getmentor.birthday}" required>

                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Cost Hire</label>
                                    <input name="cost" type="text" class="form-control form-control-lg" value="${sessionScope.getmentor.cost}" required>

                                </div>
                            </div>
                        </section>

                    </div>
                    </div>
            </form>
                                    </main>
            <jsp:include page="guest/Footer.jsp" />
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


