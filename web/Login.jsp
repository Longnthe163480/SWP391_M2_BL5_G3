<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="assets/css/stylelog.css">
        <title>Login</title>
    </head>
    <body>
        <div class="wrapper">
            <nav class="nav">
                <div class="nav-logo">
                    <p>LOGO</p>
                </div>
                <div class="nav-menu" id="navMenu">
                    <ul>
                        <li><a href="ViewTop3Mentor" class="link active">Home</a></li>        
                    </ul>
                </div>
                <div class="nav-button">
                    <button class="btn white-button" id="loginBtn" onclick="window.location.href='Login.jsp'">
                        Sign in
                    </button>
                    <button class="btn" id="registerBtn" onclick="window.location.href='Register.jsp'">
                        Sign up
                    </button>
                </div>
                <div class="nav-menu-btn">
                    <i id="navMenuBtn" class="bx bx-menu" onclick="myMenuFunction()"></i>
                </div>
            </nav>
            <!------------------------------------FORM BOX------------------------------------------->
            <div class="form-box">
                <!----------------------------------LOGIN------------------------------------------>
                <div class="login-container" id="login">
                    <form action="Login" method="post" id="loginForm">
                        <div class="top">
                            <span>Don't have an account? <a href="Register.jsp">Sign up</a></span>
                            <header>Log In</header>
                        </div>	
                        <div class="top" style="color: red">
                            <span>${err}</span>
                        </div>
                            <c:if test="${param.msg == 'success'}">
                                <div class="successMsg">
                                    ✅ Account created successfully. Please log in.
                                </div>
                            </c:if>
                        <div class="input-box">
                            <input type="text" name="username" class="input-field" placeholder="Username or Email">
                            <i class="bx bx-user"></i>
                        </div>
                        <div class="input-box">
                            <input type="password" name="password" class="input-field" placeholder="Password">
                            <i class="bx bx-lock-alt"></i>
                        </div>
                        <div class="input-box">
                            <input type="submit" class="submit" value="Sign in"/>
                            <span id="loading" style="display: none;">Loading...</span>
                        </div>
                        <div class="two-col">
                            <div class="one">
                                <input type="checkbox" name="remember" id="login-check"}>
                                <label for="login-check">Remember me</label>
                            </div>
                            <div class="two">
                                <label><a href="forgot">Forgot password?</a></label>
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

            document.getElementById("loginForm").addEventListener("submit", function(e) {
                var username = document.querySelector("input[name='username']").value;
                var password = document.querySelector("input[name='password']").value;
                var submitBtn = document.querySelector(".submit");
                var loading = document.getElementById("loading");
                if (!username || !password) {
                    e.preventDefault();
                    alert("Vui lòng điền đầy đủ tên đăng nhập và mật khẩu.");
                } else {
                    submitBtn.disabled = true;
                    loading.style.display = "inline";
                }
            });
        </script>
    </body>
</html>