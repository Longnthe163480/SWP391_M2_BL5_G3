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
                        <div>
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/SWP391_M2_BL5_G3/Login&response_type=code&client_id=921239330442-gitcr3jev4vipa87ptm2l178ajl8k23r.apps.googleusercontent.com&approval_prompt=force" class="btn btn-lg btn-danger">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-google" viewBox="0 0 16 16">
                                <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z" />
                                </svg>
                                <span class="ms-2 fs-6">Sign in with Google</span>
                            </a>
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
                                <label><a href="ResetPassword">Forgot password?</a></label>
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