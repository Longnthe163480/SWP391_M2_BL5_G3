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
        <c:if test="${sessionScope.account.roleid==1}">
            <jsp:include page="guest/Header.jsp" />
            <<main id="main">
            <section class="breadcrumbs">
                <div class="container">

                    <div class="d-flex justify-content-between align-items-center">
                        <h2>Edit Profile </h2>
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
            <form class="" action="EditMenteeProfile?accountid=${sessionScope.account.id}&menteeid=${sessionScope.getmentee.id}" method="post">
                    <div class="cr" >
                <!--Login part--> 
                    <div class="" style="height: 600px;  flex: 1.5;">

                        <h2 class="text-center text-primary fw-bold">Edit Profile</h2>


                        <!--Username input--> 
                        <div class="mb-3">
                            <label class="form-label">Name <span class="text-danger">*</span></label>
                            <input name="name" type="text" class="form-control form-control-lg" value="${sessionScope.getmentee.name}" required pattern="[\p{L}\s]+" title="Name must contain only letters and spaces">   
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Sex <span class="text-danger">*</span></label><br>
                            <input type="radio" name="sex" value="M" required 
                                <c:if test="${sessionScope.getmentee.sex == 'M'}">checked</c:if>
                            />Male
                            <input type="radio" name="sex" value="F" 
                                <c:if test="${sessionScope.getmentee.sex == 'F'}">checked</c:if>
                            />Female
                        </div>
                        <!--Password input--> 
                        <div class="mb-3">
                            <label class="form-label">Address <span class="text-danger">*</span></label>
                            <input name="address" type="text" class="form-control form-control-lg" value="${sessionScope.getmentee.address}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email <span class="text-danger">*</span></label>
                            <input name="email" type="email" class="form-control form-control-lg" value="${sessionScope.account.email}" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Please enter a valid email address">
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
                                        <img src="assets/img/mentor/${sessionScope.getmentee.avatar}" class="testimonial-img" alt="">                   
                                    </div>                                              
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Phone <span class="text-danger">*</span></label>
                                    <input name="phone" type="tel" class="form-control form-control-lg" value="${sessionScope.getmentee.phone}" required pattern="[0-9]{10,11}" title="Phone number must be 10-11 digits">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Birthday <span class="text-danger">*</span></label>
                                    <input name="birthday" type="date" class="form-control form-control-lg" value="${sessionScope.getmentee.birthday}" required 
                                           onchange="validateAge(this)" max="2006-01-01" title="You must be at least 18 years old">
                                </div>
                                
                                <script>
                                    function validateAge(input) {
                                        const today = new Date();
                                        const birthDate = new Date(input.value);
                                        const age = today.getFullYear() - birthDate.getFullYear();
                                        const monthDiff = today.getMonth() - birthDate.getMonth();
                                        
                                        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
                                            age--;
                                        }
                                        
                                        if (age < 18) {
                                            input.setCustomValidity('You must be at least 18 years old');
                                        } else {
                                            input.setCustomValidity('');
                                        }
                                    }
                                </script>
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

