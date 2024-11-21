<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15/11/2024
  Time: 12:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FPT Polytechnic</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<c:if test="${!empty sessionScope.user}"/>
<c:if test="${!empty sessionScope.guestCount}"/>
<body>
<nav class="navbar navbar-expand-sm bg-secondary-subtle">
    <div class="container">
        <a class="navbar-brand" href="/">
            <img src="images/logo-energy-pilates.png" alt="logo" width="150px">
        </a>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/likedVideos"><i class="fa-solid fa-heart"></i> Video đã thích</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                        <i class="fa-solid fa-user"></i> ${sessionScope.get("user")==null?"Tài khoản":sessionScope.user.fullname}<span class="text-danger">${param.auth == 0?" <-- Tài khoản không được cấp quyền truy cập!":""}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#" ${sessionScope.get("user")!=null?"hidden":""} role="button" data-bs-toggle="modal" data-bs-target="#login">Đăng nhập</a></li>
                        <li><a class="dropdown-item" href="#" ${sessionScope.get("user")==null?"hidden":""}>Thông tin tài khoản</a></li>
                        <li><a class="dropdown-item" href="#" ${sessionScope.get("user")==null?"hidden":""}>Đổi mật khẩu</a></li>
                        <li><a class="dropdown-item" href="#">Quên mật khẩu</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/index?logout" ${sessionScope.get("user")==null?"hidden":""}>Đăng xuất</a></li>
                        <li><a class="dropdown-item" href="#" ${sessionScope.get("user")!=null?"hidden":""} role="button" data-bs-toggle="modal" data-bs-target="#signUp">Đăng ký</a></li>
                    </ul>
                </li>
            </ul>
            <!-- <ul class="navbar-nav d-flex">
                  <li class="nav-item">
                    <a class="nav-link">Tiếng Việt</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link">Tiếng Anh</a>
                  </li>
                </ul> -->
        </div>
    </div>
</nav>


<!-- Main Content -->
<div class="container my-4">
    <h2 class="text-center">Video theo phổ biến</h2>
    <!-- Videos -->
    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card">
                <a class="text-decoration-none text-reset" href="${pageContext.request.contextPath}/videoDetail">
                    <img src="https://placehold.co/400x250" class="card-img-top" alt="Poster">
                    <div class="card-body">
                        <h5 class="card-title">Video 1</h5>
                        <div class="btn-wrapper float-end mb-3">
                            <!-- <i class="fa-solid fa-thumbs-up"></i> -->
                            <button class="btn btn-primary"><i class="fa-regular fa-thumbs-up"></i> Like</button>
                            <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#share"><i
                                    class="fas fa-share"></i> Share</button>
                        </div>
                    </div>
                </a>
            </div>
        </div>

    </div>

    <div class="btn-wrapper text-center">
        <button class="btn btn-secondary"><i class="fas fa-fast-backward"></i></button>
        <button class="btn btn-secondary"><i class="fa-solid fa-left-long"></i></button>
        <button class="btn btn-secondary"><i class="fa-solid fa-right-long"></i></button>
        <button class="btn btn-secondary"><i class="fas fa-fast-forward"></i></button>
    </div>
</div>

<!-- Footer -->
<footer class="bg-dark text-white text-center p-4">
    &copy;Copyright by Hoàng Thụy<br>
    ${sessionScope.get("guestCount")} người xem
</footer>

<!-- Share box modal -->
<div class="modal" id="share">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Share tới bạn bè</h4>
                <button class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <form class="was-validated">
                    <!-- Email input -->
                    <div data-mdb-input-init class="form-outline mb-4">
                        <label class="form-label">Gửi tới email:</label>
                        <input type="email" class="form-control" required />
                    </div>

                    <!-- Submit button -->
                    <button data-mdb-ripple-init type="button" class="btn btn-success btn-block mb-4">Send</button>
                </form>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button class="btn btn-danger" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>

</div>

<!-- Login modal -->
<div class="modal" id="login">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Đăng nhập</h4>
                <button class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <form class="was-validated" method="post">
                    <!-- Email input -->
                    <div data-mdb-input-init class="form-outline mb-4">
                        <label class="form-label">Email:</label>
                        <input type="email" name="email" class="form-control" required />

                        <label class="form-label">Password:</label>
                        <input type="password" name="password" class="form-control" required />

                        <input type="checkbox" class="form-check-input" name="remember">
                        <label class="form-check-label">Remember me</label>

                        <div class="text-danger">${param.login == 1?"Sai email hoặc mật khẩu!"
                        :param.login == 0?"Bạn phải đăng nhập mới sử dụng được chức năng này!":""}</div>
                    </div>
                    <!-- Submit button -->
                    <button formaction="/index" data-mdb-ripple-init type="submit" class="btn btn-success btn-block mb-4">Login</button>
                </form>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button class="btn btn-danger" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>

</div>

<!-- Sign Up modal -->
<div class="modal" id="signUp">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Đăng ký</h4>
                <button class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <form class="was-validated">
                    <!-- Email input -->
                    <div data-mdb-input-init class="form-outline mb-4">
                        <div class="row">
                            <div class="col-6">
                                <label class="form-label">Username:</label>
                                <input type="text" class="form-control"name="username" required />

                                <label class="form-label">Fullname:</label>
                                <input type="text" class="form-control" name="fullname" required />
                            </div>

                            <div class="col-6">
                                <label class="form-label">Password:</label>
                                <input type="text" class="form-control" required />

                                <label class="form-label">Username:</label>
                                <input type="text" class="form-control" name="email" required />

                            </div>
                        </div>

                    </div>

                    <!-- Submit button -->
                    <button data-mdb-ripple-init type="button" class="btn btn-success btn-block mb-4">Sign Up</button>
                </form>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button class="btn btn-danger" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>

</div>

<c:if test="${param.login != null}">
    <span id="login-modal-trigger" data-bs-toggle="modal" data-bs-target="#login" style="display: none;"></span>
    <script>
        document.getElementById('login-modal-trigger').click();
    </script>
</c:if>

</body>

</html>