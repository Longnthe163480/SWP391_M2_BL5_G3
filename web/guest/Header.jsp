<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>HomePage</title>
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
        <!-- ======= Header ======= -->
        <header id="header" class="fixed-top d-flex align-items-center ">
            <div class="container d-flex justify-content-between align-items-center">

                <div class="logo">
                    <h1 class="text-light"><a href="ViewTop3Mentor"><span>HappyProgamming</span></a></h1>
                    <!-- Uncomment below if you prefer to use an image logo -->
                    <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
                </div>

                <nav id="navbar" class="navbar">
                    <ul>
                        <li><a class="" href="ViewTop3Mentor">Home</a></li>
                        <li><a href="ViewAllMentor">Mentor</a></li>
                        <c:if test="${sessionScope.account==null}">
                        <li><a href="SignIn.jsp">Sign In</a></li>
                        <li><a href="SignUp.jsp">Sign Up</a></li> 
                        </c:if>
                        <c:if test="${sessionScope.account!=null}">
                                <c:if test="${sessionScope.getmentee!=null}">
                                <li class="dropdown"><a href="#"><span>Hello ${sessionScope.getmentee.name} </span> <i class="bi bi-chevron-down"></i></a>
                                    </c:if>
                                    <c:if test="${sessionScope.getmentor!=null}">
                                <li class="dropdown"><a href="#"><span>Hello ${sessionScope.getmentor.name} </span> <i class="bi bi-chevron-down"></i></a>
                                    </c:if>
                                <ul>
                                    <li><a href="ChangePassword.jsp">Change Pasword</a></li>
                                    <c:if test="${sessionScope.getmentee!=null}">
                                        <li><a href="ViewMenteeProfile?accmenteeid=${sessionScope.getmentee.accountid}">Profile</a></li>
                                        <li><a href="ViewAllRequest?menteeid=${sessionScope.getmentee.id}">My Request</a></li>
                                        <li><a href="ViewAllHireRequest?menteeid=${sessionScope.getmentee.id}">My Hire Request</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.getmentor!=null}">
                                    <li><a href="ViewMentorProfile?accmentorid=${sessionScope.getmentor.accountid}">Profile</a></li>
                                     <li><a href="ViewAllRequest?mentorid=${sessionScope.getmentor.id}">My Request</a></li>
                                        </c:if>
                                     <c:if test="${sessionScope.account.roleid==3}">
                                    <li><a href="ViewAllAccount">Account List</a></li>
                                    </c:if>
                                    <li><a href="LogOut?accountid=${sessionScope.account.id}">Sign Out</a></li>
                                    
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav><!-- .navbar -->

            </div>
        </header><!-- End Header -->
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
