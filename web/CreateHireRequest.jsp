<%-- 
    Document   : CreateHireRequest
    Created on : Apr 25, 2025, 9:56:15 AM
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>New Hire Request</title>

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
        <c:if test="${sessionScope.account.roleid==1}">
            <jsp:include page="guest/Header.jsp"></jsp:include>
                <!-- ======= Hero Section ======= -->
                <section class="breadcrumbs">
                    <div class="container">

                        <div class="d-flex justify-content-between align-items-center">
                            <h2>New Hire Request</h2>
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
                            <form class="" action="CreateHireRequest?menteeid=${sessionScope.getmentee.id}" method="post" >
                    <div class="cr" >
                        <div class="col-md-6 col-lg-6 col-xl-4 offset-xl-1" style="height: 600px; ">
                            <h2 class="text-center text-primary fw-bold">New Hire Request</h2>                                   
                        <!-- Username input -->
                        <div class="form-outline mb-4">
                            <label class="form-label">Title</label>
                            <input name="title" type="text" class="form-control form-control-lg" required>   
                        </div>
                        <div class="form-outline mb-3" >
                            <label class="form-label">Content</label>
                            <textarea name="content" rows="4" class="form-control form-control-lg" required></textarea>
                        </div>

                        <div class="text-center text-lg-start mt-4 pt-2 mb-4" style="position: relative">
                            <button type="submit" class="btn btn-outline-primary btn-lg"style="padding-left: 2.5rem; padding-right: 2.5rem;">
                                Create
                            </button>
                            <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute; margin-left: 20px">Back</button>
                        </div>            
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-4 offset-xl-1" style="height: 600px; margin-right: 100px">
                        <section class="testimonials" data-aos="fade-up">
                            <div class="container">
                                <div class="section-title">
                                    <h2>Choose Mentor</h2>
                                </div>
                                <p style="text-align: center"> You just choose one mentor</p>
                                <div class="testimonials-carousel swiper">
                                    <div class="swiper-wrapper">
                                        <c:forEach items="${sessionScope.listallmentor}" var="l">
                                            <div class="testimonial-item swiper-slide">
                                                <img src="assets/img/mentor/${l.avatar}" class="testimonial-img" alt="">
                                                <h3>${l.name}</h3>
                                                <h4>Cost Hire:</h4>
                                                <p>
                                                    <i class="bx bxs-quote-alt-left quote-icon-left"></i>
                                                    ${l.cost}$
                                                    <i class="bx bxs-quote-alt-right quote-icon-right"></i>                                      
                                                </p>
                                                <input type="radio" name="choosementor" value="${l.id}"/>
                                            </div>
                                        </c:forEach>                            
                                    </div>
                                    <div class="swiper-pagination"></div>
                                </div>
                            </div>
                        </section>                
                    </div>                  
                </div>
                    
            </form>
            <jsp:include page="guest/Footer.jsp"></jsp:include>
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
