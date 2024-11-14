<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15/11/2024
  Time: 12:33 AM
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

<body>
<nav class="navbar navbar-expand-sm bg-secondary-subtle">
    <div class="container">
        <a class="navbar-brand" href="#">
            <img src="images/logo-energy-pilates.png" alt="logo" width="150px">
        </a>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#"><i class="fa-solid fa-heart"></i> Video đã thích</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                        <i class="fa-solid fa-user"></i> Tài khoản
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Đăng nhập</a></li>
                        <li><a class="dropdown-item" href="#">Thông tin tài khoản</a></li>
                        <li><a class="dropdown-item" href="#">Đổi mật khẩu</a></li>
                        <li><a class="dropdown-item" href="#">Quên mật khẩu</a></li>
                        <li><a class="dropdown-item" href="#">Đăng xuất</a></li>
                        <li><a class="dropdown-item" href="#">Đăng ký</a></li>
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
    <div class="row">
        <div class="col-md-9 mb-4">
            <div class="card">
                <img src="https://placehold.co/400x250" class="card-img-top" alt="Poster">
                <div class="card-body">
                    <h4 class="card-title">Video 1</h4>
                    <div class="description">
                        <h6>Lorem ipsum dolor sit amet consectetur adipisicing elit. Tempora, laboriosam obcaecati, quod ipsam
                            doloribus voluptas dignissimos maiores labore eum, quia porro repudiandae. Accusamus, aut dolor
                            assumenda incidunt mollitia dicta nam.</h6>
                    </div>
                    <div class="btn-wrapper float-end">
                        <!-- <i class="fa-solid fa-thumbs-up"></i> -->
                        <button class="btn btn-primary"><i class="fa-regular fa-thumbs-up"></i> Like</button>
                        <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#share"><i
                                class="fas fa-share"></i> Share</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-3 mb-4">
            <div class="d-flex align-items-center gap-2 p-2 mb-3 border rounded-2">
                <img src="https://placehold.co/600x400" class="float-start" alt="thumbnail" width="100px">
                <h6>Video 1</h6>
            </div>

            <div class="d-flex align-items-center gap-2 p-2 mb-3 border rounded-2">
                <img src="https://placehold.co/600x400" class="float-start" alt="thumbnail" width="100px">
                <h6>Video 1</h6>
            </div>

            <div class="d-flex align-items-center gap-2 p-2 mb-3 border rounded-2">
                <img src="https://placehold.co/600x400" class="float-start" alt="thumbnail" width="100px">
                <h6>Video 1</h6>
            </div>

            <div class="d-flex align-items-center gap-2 p-2 mb-3 border rounded-2">
                <img src="https://placehold.co/600x400" class="float-start" alt="thumbnail" width="100px">
                <h6>Video 1</h6>
            </div>

        </div>
    </div>
</div>

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
                        <label class="form-label" for="email">Gửi tới email:</label>
                        <input type="email" id="email" class="form-control" required />
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

</body>

</html>