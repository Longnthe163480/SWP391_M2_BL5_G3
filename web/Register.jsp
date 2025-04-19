<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="assets/css/stylelog.css">
        <title>Register</title>
    </head>
    <body>
        <div class="wrapper">
            <nav class="nav">
                <div class="nav-logo">
                    <p>LOGO</p>
                </div>
                <div class="nav-menu" id="navMenu">
                    <ul>
                        <li><a href="home" class="link active">Home</a></li>
                    </ul>
                </div>
                <div class="nav-button">
                    <button class="btn" id="loginBtn" onclick="window.location.href='Login.jsp'">
                        Sign in
                    </button>
                    <button class="btn white-button" id="registerBtn" onclick="window.location.href='Register.jsp'">
                        Sign up
                    </button>
                </div>
                <div class="nav-menu-btn">
                    <i id="navMenuBtn" class="bx bx-menu" onclick="myMenuFunction()"></i>
                </div>
            </nav>
            <!------------------------------------FORM BOX------------------------------------------->
            <div class="form-box">
                <!----------------------------------REGISTER------------------------------------------>
                <div class="register-container" id="register">
                    <form action="signup" method="post">
                        <div class="top">
                            <span>Have an account? <a href="Login.jsp">Login</a></span>
                            <header>SIGN UP</header>
                        </div>
                        <div class="two-forms">
                            <div class="input-box" style="width: 50%;">
                                <input type="text" name="fullname" class="input-field" placeholder="Fullname" required value="${param.fullname}">
                                <i class="bx bx-user"></i>
                            </div>
                            <div style="width: 50%;" class="input-box">
                                <select class="condition" name="gender" required>
                                    <option value="Male" ${param.gender == 'Male' ? 'selected' : ''}>Male</option>
                                    <option value="Female" ${param.gender == 'Female' ? 'selected' : ''}>Female</option>
                                </select>
                                <i class="bx bx-male-female"></i>
                            </div>
                        </div>
                        <div class="two-forms">
                            <div style="width: 50%;" class="input-box">
                                <select class="condition" name="role" required>
                                    <option value="Mentee" ${param.role == 'Mentee' ? 'selected' : ''}>Mentee</option>
                                    <option value="Mentor" ${param.role == 'Mentor' ? 'selected' : ''}>Mentor</option>
                                </select>
                                <i class="bx bx-id-card"></i>
                            </div>
                            <div style="width: 50%;" class="input-box">
                            </div>
                        </div>
                        <div class="input-box">
                            <c:set var="errorType1" value="${errorMsg.contains('username')}"/>
                            <input type="text" name="newusername" class="input-field ${errorType1 ? 'input-field-error' : ''}" placeholder="Username" required value="${param.newusername}">
                            <i class="bx bx-user"></i>
                        </div>
                        <div class="two-forms">
                            <c:set var="errorType2" value="${errorMsg.contains('Password')}"/>
                            <div class="input-box" style="width: 50%;">
                                <input type="password" name="newpassword" class="input-field ${errorType2 ? 'input-field-error' : ''}" placeholder="Password" required value="${param.newpassword}" pattern=".{6,}" title="at least 6 chars">
                                <i class="bx bx-lock-alt"></i>
                            </div>
                            <div class="input-box" style="width: 50%;">
                                <input type="password" name="repass" class="input-field ${errorType2 ? 'input-field-error' : ''}" placeholder="Re-Password" required value="${param.repass}">
                                <i class="bx bx-lock-alt"></i>
                            </div>
                        </div>
                        <div class="input-box">
                            <c:set var="errorType3" value="${errorMsg.contains('email')}"/>
                            <input type="text" name="newemail" class="input-field ${errorType3 ? 'input-field-error' : ''}" placeholder="Email" required value="${param.newemail}">
                            <i class="bx bx-envelope"></i>
                        </div>
                        <div class="input-box">
                            <input type="date" name="dob" id="dob" class="input-field" placeholder="Dob" required value="${param.dob}">
                            <i class="bx bx-user"></i>
                        </div>
                        <c:if test="${not empty errorMsg}">
                            <div class="errorMsg" style="color: red; background-color: rgba(255, 255, 255, 0.7); padding: 10px; margin-bottom: 20px; border-radius: 30px;">
                                ${errorMsg}
                            </div>
                        </c:if>
                        <div class="input-box">
                            <input type="submit" class="submit" value="REGISTER"/>
                        </div>
                        <div class="two-col">
                            <div class="one">
                            </div>
                            <div class="two">
                                <label><a href="#">Terms-Condition</a></label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>
            function myMenuFunction() {
                var i = document.getElementById('navMenu');
                var menuBtn = document.getElementById('navMenuBtn');
                if (i.className === 'nav-menu') {
                    i.className += ' responsive';
                    menuBtn.className = 'bx bx-x';
                } else {
                    i.className = 'nav-menu';
                    menuBtn.className = 'bx bx-menu';
                }
            }

            const currentDate = new Date();
            const dateFormat = currentDate.toISOString().split('T')[0];
            document.getElementById('dob').setAttribute('max', dateFormat);
        </script>
    </body>
</html>