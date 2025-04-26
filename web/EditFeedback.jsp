<%-- 
    Document   : EditFeedback
    Created on : Apr 26, 2025, 7:28:27 PM
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
                text-align: center;
                justify-content: space-between;
                width: 97%;
                margin-bottom: 20px;
                margin-top: 20px;
                margin-left: 20px;
            }
            .start{

	color: green;
}

.stop{
	color: red;
}


.rate{

	border-bottom-right-radius: 12px;
	border-bottom-left-radius: 12px;

}



.rating {
    display: flex;
    flex-direction: row-reverse;
    justify-content: center
}

.rating>input {
    display: none
}

.rating>label {
    position: relative;
    width: 1em;
    font-size: 30px;
    font-weight: 300;
    color: #FFD600;
    cursor: pointer
}

.rating>label::before {
    content: "\2605";
    position: absolute;
    opacity: 0
}

.rating>label:hover:before,
.rating>label:hover~label:before {
    opacity: 1 !important
}

.rating>input:checked~label:before {
    opacity: 1
}

.rating:hover>input:checked~label:before {
    opacity: 0.4
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
                            <h2>Edit Feedback</h2>
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
                            <form class="" action="EditFeedback?feedbackid=${feedback.id}" method="post">
                <div class="cr" >
                        <h2 class="text-center text-primary fw-bold">Edit Feedback</h2>                                   
                        <!-- Username input -->
                        <div class="form-outline mb-4">
                            <div class="rate bg-success py-3 text-white mt-3">

                                <h6 class="mb-0">Rate your driver</h6>

                                <div class="rating"> 
                                    <input type="radio" name="star" value="5" id="5"><label for="5">☆</label>
                                    <input type="radio" name="star" value="4" id="4"><label for="4">☆</label> 
                                    <input type="radio" name="star" value="3" id="3"><label for="3">☆</label> 
                                    <input type="radio" name="star" value="2" id="2"><label for="2">☆</label> 
                                    <input type="radio" name="star" value="1" id="1"><label for="1">☆</label>
                                </div>
                            </div>
                        <div class="form-outline mb-3" >
                            <label class="form-label">Comment</label>
                            <textarea name="comment" rows="4" class="form-control form-control-lg" required> ${feedback.comment}</textarea>
                        </div>

                        <div class="text-center text-lg-start mt-4 pt-2 mb-4">
                            <button type="submit" class="btn btn-outline-primary btn-lg"style="padding-left: 2.5rem; padding-right: 2.5rem; position: relative">
                                Save
                            </button>
                            <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute; margin-left: 10px">Back</button>
                        </div>    
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
