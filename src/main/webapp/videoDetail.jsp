<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<c:if test="${!empty sessionScope.user}"/>
<c:url value="/videoDetail?id=${video.id}" var="url"/>
<body>
<nav class="navbar navbar-expand-sm bg-secondary-subtle">
    <div class="container">
        <a class="navbar-brand" href="/">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="logo" width="150px">
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
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin" ${sessionScope.get("user").admin?"":"hidden"}>Administration tools</a></li>
                        <li><a class="dropdown-item" href="#" ${sessionScope.get("user")==null?"hidden":""}>Đổi mật khẩu</a></li>
                        <li><a class="dropdown-item" href="#">Quên mật khẩu</a></li>
                        <li><a class="dropdown-item" href="${url}&logout" ${sessionScope.get("user")==null?"hidden":""}>Đăng xuất</a></li>
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
    <div class="row">
        <div class="col-md-9 mb-4">
            <div class="card">
                <div class="ratio ratio-16x9">
                    <iframe src="https://www.youtube.com/embed/${fn:substring(video.poster, 17, -1)}"></iframe>
                </div>
                <div class="card-body">
                    <h4 class="card-title">${video.title}</h4>
                    <div class="description">
                        <h6>${video.description}</h6>
                    </div>
                    <div class="btn-wrapper float-end">
                        <!-- <i class="fa-solid fa-thumbs-up"></i> -->
                        <form method="post">
                            <c:choose>
                                <c:when test="${listLikedVideos.contains(video)}">
                                    <button class="btn btn-danger" type="submit" formaction="${url}&unlike=1">
                                        <i class="fa-solid fa-thumbs-up"></i> Unlike</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-primary" type="submit" formaction="${url}&like=1">
                                        <i class="fa-regular fa-thumbs-up"></i> Like</button>
                                </c:otherwise>
                            </c:choose>
                            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#share"><i
                                    class="fas fa-share"></i> Share</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-3 mb-4">
            <c:forEach items="${listVideos}" var="video">
                <c:set var="ytbId" value="${fn:substring(video.poster, 17, 28)}"/>
                    <a class="text-decoration-none text-reset" href="${pageContext.request.contextPath}/videoDetail?id=${video.id}">
                        <div class="d-flex align-items-center gap-2 p-2 mb-3 border rounded-2">
                                <img src="https://img.youtube.com/vi/${ytbId}/hqdefault.jpg" class="float-start" alt="thumbnail" width="100px">
                                <h6>${video.title}</h6>
                        </div>
                    </a>
            </c:forEach>

        </div>
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
                <form class="was-validated" method="post">
                    <!-- Email input -->
                    <div data-mdb-input-init class="form-outline mb-4">
                        <label class="form-label">Gửi tới email:</label>
                        <input type="email" class="form-control" name="toEmail" required />
                        <div class="text-success">${param.share == 1?"Share thành công!":""}</div>
                    </div>


                    <!-- Submit button -->
                    <button formaction="${url}&share=1" data-mdb-ripple-init type="submit" class="btn btn-success btn-block mb-4">Send</button>
                </form>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button class="btn btn-danger" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>

</div>

<!-- Like box modal -->
<div class="modal" id="like">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Like thành công!</h4>
                <button class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button class="btn btn-danger" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>

</div>

<!-- Unlike box modal -->
<div class="modal" id="unlike">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Unlike thành công!</h4>
                <button class="btn-close" data-bs-dismiss="modal"></button>
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
                        <label class="form-label">Email hoặc Username:</label>
                        <input type="text" name="email" class="form-control" required />

                        <label class="form-label">Password:</label>
                        <input type="password" name="password" class="form-control" required />

                        <input type="checkbox" class="form-check-input" name="remember">
                        <label class="form-check-label">Remember me</label>

                        <div class="text-danger">${param.login == 1?"Sai email hoặc mật khẩu!"
                                :param.login == 0?"Bạn phải đăng nhập mới sử dụng được chức năng này!":""}</div>
                    </div>
                    <!-- Submit button -->
                    <button formaction="${url}" data-mdb-ripple-init type="submit" class="btn btn-success btn-block mb-4">Login</button>
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
                <form class="was-validated" method="post">
                    <!-- Email input -->
                    <div data-mdb-input-init class="form-outline mb-4">
                        <div class="row">
                            <div class="col-6">
                                <label class="form-label">Username:</label>
                                <input type="text" class="form-control"name="id" required />

                                <label class="form-label">Password:</label>
                                <input type="password" class="form-control" name="password" required />
                            </div>

                            <div class="col-6">
                                <label class="form-label">Fullname:</label>
                                <input type="text" class="form-control" name="fullname" required />

                                <label class="form-label">Email:</label>
                                <input type="email" class="form-control" name="email" required />

                            </div>

                            <div class="text-danger">${param.reg == 0?"Username đã tồn tại!":""}</div>
                        </div>

                    </div>

                    <!-- Submit button -->
                    <button formaction="?reg=1" data-mdb-ripple-init type="submit" class="btn btn-success btn-block mb-4">Sign Up</button>
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

<c:if test="${param.reg == 0}">
    <span id="signUp-modal-trigger" data-bs-toggle="modal" data-bs-target="#signUp" style="display: none;"></span>
    <script>
        document.getElementById('signUp-modal-trigger').click();
    </script>
</c:if>

<c:if test="${param.share != null}">
    <span id="share-modal-trigger" data-bs-toggle="modal" data-bs-target="#share" style="display: none;"></span>
    <script>
        document.getElementById('share-modal-trigger').click();
    </script>
</c:if>

<c:if test="${param.like == 1}">
    <span id="like-modal-trigger" data-bs-toggle="modal" data-bs-target="#like" style="display: none;"></span>
    <script>
        document.getElementById('like-modal-trigger').click();
    </script>
</c:if>

<c:if test="${param.unlike == 1}">
    <span id="unlike-modal-trigger" data-bs-toggle="modal" data-bs-target="#unlike" style="display: none;"></span>
    <script>
        document.getElementById('unlike-modal-trigger').click();
    </script>
</c:if>
</body>

</html>