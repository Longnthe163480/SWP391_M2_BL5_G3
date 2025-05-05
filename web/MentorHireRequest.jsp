<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Hire Requests</title>
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
    </head>
    <body>
        <c:if test="${sessionScope.account.roleid==2}">
            <jsp:include page="guest/Header.jsp" />
            <main id="main" style="position: relative">
                <section class="breadcrumbs">
                    <div class="container">
                        <div class="d-flex justify-content-between align-items-center">
                            <h2>Hire Requests</h2>
                            <ol>
                                <li><a href="ViewTop3Mentor">Home</a></li>
                            </ol>
                        </div>
                    </div>
                </section>
                
                <c:if test="${error != null}">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:if>

                <div class="d-flex">
                    <form action="SearchHireRequest?mentorid=${sessionScope.getmentor.id}" method="post">
                        <div class="input-group" style="margin: 10px; padding-right: 500px;padding-left: 100px;">
                            <input value="${searchtext}" type="text" class="form-control" placeholder="Search" name="name">
                            <button type="submit" class="btn btn-secondary"><i class="bi-search"></i></button>  
                        </div> 
                    </form>
                </div>

                <div class="container-fluid h-custom">
                    <div class="row d-flex justify-content-start align-items-center h-100">
                        <table class="table table-striped table-hover" style="margin: 50px; width:93%">
                            <tr>
                                <th>ID</th>
                                <th>Mentee Name</th>
                                <th>Title</th>
                                <th>Content</th>
                                <th>Cost Hire</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach items="${hirerequests}" var="c">
                                <tr>
                                    <td>${c.id}</td>
                                    <td>${c.mentorname}</td>
                                    <td>${c.title}</td>
                                    <td>${c.content}</td>
                                    <td>${c.costhire}$</td>
                                    <td>${c.status}</td>
                                    <td>
                                        <c:if test="${c.status == 'Pending' || c.status == 'not yet'}">
                                            <form action="UpdateHireRequestStatus" method="post" style="display: inline;">
                                                <input type="hidden" name="requestId" value="${c.id}">
                                                <input type="hidden" name="statusId" value="1">
                                                <button type="submit" class="btn btn-success">Accept</button>
                                            </form>
                                            <form action="UpdateHireRequestStatus" method="post" style="display: inline;">
                                                <input type="hidden" name="requestId" value="${c.id}">
                                                <input type="hidden" name="statusId" value="2">
                                                <button type="submit" class="btn btn-danger">Reject</button>
                                            </form>
                                        </c:if>
                                        <c:if test="${c.status != 'Pending' && c.status != 'not yet'}">
                                            <span class="badge ${c.status == 'Accepted' ? 'bg-success' : 'bg-danger'}">${c.status}</span>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

                <div class="d-flex justify-content-center">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${endpage}" var="i">
                                <li class="page-item ${tag == i ? 'active' : ''}">
                                    <a class="page-link" href="ViewMentorHireRequest?mentorid=${sessionScope.getmentor.id}&index=${i}">${i}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </div>

                <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute; margin-left: 20px">Back</button>
            </main>
            <jsp:include page="guest/Footer.jsp" />
        </c:if>
        <c:if test="${sessionScope.account.roleid!=2}">
            <jsp:include page="error.jsp" />
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
