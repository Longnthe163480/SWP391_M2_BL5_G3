<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "entity.*" %>
<%@page import = "java.util.*" %>
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
        <header id="header" class="fixed-top d-flex align-items-center header-transparent">
            <div class="container d-flex justify-content-between align-items-center">

                <div class="logo">
                    <h1 class="text-light"><a href="ViewTop3Mentor"><span>HappyProgamming</span></a></h1>
                    <!-- Uncomment below if you prefer to use an image logo -->
                    <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
                </div>

                <nav id="navbar" class="navbar">
                    <ul>
                        <li><a class="active " href="ViewTop3Mentor">Home</a></li>
                        <li><a href="ViewAllMentor">Mentor</a></li>
                        <c:if test="${sessionScope.account==null}">
                        <li><a href="Login">Sign In</a></li>
                        <li><a href="Register.jsp">Sign Up</a></li>
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
                                    <li><a href="ViewAllMentee">Mentee List</a></li>
                                    <li><a href="AdminSkillManager">Skill Manager</a></li>
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

        <section id="hero" class="d-flex justify-cntent-center align-items-center">
            <div id="heroCarousel" class="container carousel carousel-fade" data-bs-ride="carousel" data-bs-interval="5000">

                <!-- Slide 1 -->
                <div class="carousel-item active">
                    <div class="carousel-container">
                        <h2 class="animate__animated animate__fadeInDown">Welcome to <span>HappyProgamming</span></h2>
                        <p class="animate__animated animate__fadeInUp">You can bring your programming questions to our advisors for answers. Together, progress in programming and become excellent programmers in the future. Nice to meet you.</p>
                        <a href="ViewAllPost" class="btn-get-started animate__animated animate__fadeInUp">Join Forums</a>
                    </div>
                </div>

                <!-- Slide 2 -->
                <div class="carousel-item">
                    <div class="carousel-container">
                        <h2 class="animate__animated animate__fadeInDown">CODEING TOGETHER</h2>
                        <p class="animate__animated animate__fadeInUp">+Software and cathedrals are much the same – first we build them, then we pray<br>
                            +Measuring programming progress by lines of code is like measuring aircraft building progress by weight </p>                            
                        <a href="" class="btn-get-started animate__animated animate__fadeInUp">Read More</a>
                    </div>
                </div>                   

                <a class="carousel-control-prev" href="#heroCarousel" role="button" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon bx bx-chevron-left" aria-hidden="true"></span>
                </a>

                <a class="carousel-control-next" href="#heroCarousel" role="button" data-bs-slide="next">
                    <span class="carousel-control-next-icon bx bx-chevron-right" aria-hidden="true"></span>
                </a>

            </div>
        </section><!-- End Hero -->
        <main id="main">

            <!-- ======= Features Section ======= -->
            <section class="features">
                <div class="container">

                    <div class="section-title">
                        <h2>Outstanding Mentor</h2>
                    </div>
                    <% List<Mentor> list= (List<Mentor>) request.getAttribute("top3Mentor");
                       for (Mentor m : list) {
                    %>    
                    <section class="why-us section-bg" data-aos="fade-up" date-aos-delay="200">
                        <div class="container">

                            <div class="row">
                                <div class="col-lg-6 video-box">      
                                    <a href="ViewMentorDetail?mentorid=<%=m.getId()%>"><img src='assets/img/mentor/<%=m.getAvatar()%>' class="img-fluid" alt=""></a>
                                </div>

                                <div class="col-lg-6 d-flex flex-column justify-content-center p-5">

                                    <div class="icon-box">
                                        <div class="icon"><i class="bx bx-fingerprint"></i></div>
                                        <h4 class="title"><a href=""><%=m.getName()%></a></h4>
                                        <p class="description"><%=m.getIntroduce()%></p>
                                    </div>

                                    <div class="icon-box">
                                        <div class="icon"><i class="bx bx-gift"></i></div>
                                        <h4 class="title"><a href="">Rate Star</a></h4>
                                        <p class="description">Average Star: <%=(float) Math.round(m.getAverageStar() * 10) / 10%> <br>
                                            <% for(int i=1;i<m.getAverageStar();i++){ %>
                                            <span id="boot-icon" class="bi bi-star" style="font-size: 56px; color: rgb(255, 210, 48);"></span>
                                            <%}%>
                                        </p>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </section>
                    <%}%>
                </div>
            </section><!-- End Features Section -->
        </main><!-- End #main -->

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
