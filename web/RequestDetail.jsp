<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Request Detail</title>
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
                            <h2>Request Detail</h2>
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
                <!-- comment -->
                <section id="portfolio-details" class="portfolio-details">
                    <div class="container">
                        <form action="UpdateRequest.jsp" method="post">
                            <button type="submit" class="btn btn-outline-primary btn-lg" style="margin-top: 10px;margin-right: 50px;font-size: 15px;float: right; width: 200px;height: 50px;" >Edit Request</button>
                        </form>
                        <div class="row gy-4">
                            <div class="col-lg-12">
                                <div class="portfolio-info">
                                    <h3 style="text-align: center;">Title:${coderequest.title}</h3>
                                    <ul style="text-align: center;">
                                        <li><strong>Content</strong>: 
                                            ${coderequest.content}
                                        </li>
                                        <li><strong>Deadline:</strong>
                                            ${coderequest.deadline}
                                        </li>  
                                        <li><strong>Skill: </strong>
                                            <c:forEach items="${skill}" var="m">
                                                ${m.name}, 

                                            </c:forEach>
                                        </li>           
                                    </ul>
                                </div>
                                <br>
                                <div class="portfolio-info">
                                    <h3 style="text-align: center;">Mentor</h3>
                                    <ul>
                                        <p style="text-align: center;">Click avatar to see answer of Mentor for this request</p>
                                        <section class="team" data-aos="fade-up" data-aos-easing="ease-in-out" data-aos-duration="500">
                                            <div class="container">

                                                <div class="row">
                                                    <c:forEach items="${mentor}" var="me">
                                                        <div class="col-lg-4 col-md-6 d-flex align-items-stretch">
                                                            <div class="member">
                                                                <div class="member-img">
                                                                    <a href="ViewDetailAnswer?mentorid=${me.id}&coderequestid=${coderequest.id}&menteeid=${sessionScope.getmentee.id}"><img src="assets/img/mentor/${me.avatar}" class="img-fluid" alt=""></a>
                                                                    <div class="social">
                                                                        <a href=""><i class="bi bi-twitter"></i></a>
                                                                        <a href=""><i class="bi bi-facebook"></i></a>
                                                                        <a href=""><i class="bi bi-instagram"></i></a>
                                                                        <a href=""><i class="bi bi-linkedin"></i></a>
                                                                    </div>
                                                                </div>
                                                                <div class="member-info">
                                                                    <h4>${me.name}</h4>
                                                                    <span>Introduce:</span>
                                                                    <p>${me.introduce}</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </section>
                                    </ul>
                                </div>

                            </div>
                        </div>
                    </div>
                </section><!-- End Portfolio Details Section -->
                <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute;bottom: 20px;left: 20px;">Back</button>

            </main>
            <jsp:include page="guest/Footer.jsp" />
        </c:if>
            <c:if test="${sessionScope.account.roleid==2}">
                <jsp:include page="guest/Header.jsp" />
            <main id="main" style="position: relative">
                <section class="breadcrumbs">
                    <div class="container">

                        <div class="d-flex justify-content-between align-items-center">
                            <h2>Request Detail</h2>
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
                <!-- comment -->
                <section id="portfolio-details" class="portfolio-details">
                    <div class="container">
                        <c:if test="${answer!=null}">
                        <form action="UpdateAnswer.jsp" method="post">
                            <button type="submit" class="btn btn-outline-primary btn-lg" style="margin-top: 10px;margin-right: 50px;font-size: 15px;float: right; width: 200px;height: 50px;" >Edit Answer</button>
                            </form>
                         </c:if>
                        <c:if test="${answer==null}">
                        <form action="CreateAnswer.jsp" method="post">
                            <button type="submit" class="btn btn-outline-primary btn-lg" style="margin-top: 10px;margin-right: 50px;font-size: 15px;float: right; width: 200px;height: 50px;" >New Answer</button>
                            </form>
                         </c:if>
                        <div class="row gy-4">
                            <div class="col-lg-12">
                                <div class="portfolio-info">
                                    <h3 style="text-align: center;">Title:${coderequest.title}</h3>
                                    <ul style="text-align: center;">
                                        <li><strong>Content</strong>: 
                                            ${coderequest.content}
                                        </li>
                                        <li><strong>Deadline:</strong>
                                            ${coderequest.deadline}
                                        </li>  
                                        <li><strong>Skill: </strong>
                                            <c:forEach items="${skill}" var="m">
                                                ${m.name}, 

                                            </c:forEach>
                                        </li>           
                                    </ul>
                                </div>
                                <div class="portfolio-info">
                                    <h3 style="text-align: center;">Answer</h3>
                                        <c:if test="${answer==null}">
                                        <ul>
                                            <h3>Mentor doesn't answer this request.</h3>
                                        </ul>
                                    </c:if>
                                    <c:if test="${answer!=null}">
                                        <ul>
                                            <h3>Content: </h3><br>
                                            <p style="text-align: center">${answer.content}</p>                                           
                                        </ul>
                                        
                                    </c:if>   
                                </div>

                            </div>
                        </div>
                    </div>
                </section><!-- End Portfolio Details Section -->
                <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute;bottom: 20px;left: 20px;">Back</button>

            </main>
            <jsp:include page="guest/Footer.jsp" />
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
