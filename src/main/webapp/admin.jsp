<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<c:set var="pageVideoNumber" value="${pageVideoNumber}" scope="request"/>
<c:url value="/admin/" var="url"/>
<c:set value="${videoEdit}" var="videoEdit"/>
<jsp:useBean id="listVideos" scope="request" type="java.util.List<com.org.java4.entities.Video>"/>
<c:set var="listVideos" value="${listVideos}" scope="request"/>
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
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin" ${sessionScope.get("user").admin?"":"hidden"}>Administration tools</a></li>
                        <li><a class="dropdown-item" href="#" ${sessionScope.get("user")==null?"hidden":""}>Đổi mật khẩu</a></li>
                        <li><a class="dropdown-item" href="#">Quên mật khẩu</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/index?logout" ${sessionScope.get("user")==null?"hidden":""}>Đăng xuất</a></li>
                        <li><a class="dropdown-item" href="#" ${sessionScope.get("user")!=null?"hidden":""} role="button" data-bs-toggle="modal" data-bs-target="#signUp">Đăng ký</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="navbar-nav d-flex">
                <li class="nav-item">
                    <button class="btn" data-bs-toggle="collapse" data-bs-target="#home">Home</button>
                </li>

                <li class="nav-item">
                    <button class="btn" id="btnVideos" data-bs-toggle="collapse" data-bs-target="#videos">Videos</button>
                </li>

                <li class="nav-item">
                    <button class="btn" id="btnUsers" data-bs-toggle="collapse" data-bs-target="#users">Users</button>
                </li>

                <li class="nav-item">
                    <button class="btn" id="btnReports" data-bs-toggle="collapse" data-bs-target="#reports">Reports</button>
                </li>

            </ul>
        </div>
    </div>
</nav>

<%--<!-- Footer -->--%>
<%--<footer class="bg-dark text-white text-center p-4 fixed-bottom">--%>
<%--    &copy;Copyright by Hoàng Thụy<br>--%>
<%--    ${sessionScope.get("guestCount")} người xem--%>
<%--</footer>--%>

<!-- Main Content -->
<div class="container accordion my-4 min-vh-100" id="mainGroup">
    <!-- Home -->
    <div class="accordion-collapse collapse my-4 fade" id="home" data-bs-parent="#mainGroup">

    </div>

    <!-- Videos -->
    <div class="accordion-collapse collapse my-4 fade ${param.video == null || param.videoPageNumber != null?"":"show"}" id="videos" data-bs-parent="#mainGroup">
        <div class="container">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-bs-toggle="tab" href="#editVideo">Edit Video</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="tab" href="#listVideos">List Videos</a>
                </li>

            </ul>
            <!-- Tab panes -->
            <c:set var="ytbImg" value="https://img.youtube.com/vi/${fn:substring(video.poster, 17, 28)}/hqdefault.jpg"/>
            <div class="tab-content">
                <div id="editVideo" class="container w-75 tab-pane active pt-3">
                    <form action="" method="post" class="border rounded-2 p-3 was-validated">
                        <div class="row">
                            <div class="col-5">
                                <label class="form-label"><img src="${videoEdit != null?ytbImg:"https://placehold.co/400x250"}" alt=""
                                                                              width="350px"></label>
<%--                                <input class="form-control" type="file" id="formFile" hidden>--%>
                            </div>
                            <div class="col-7">
                                <label class="form-label" for="id">Id</label>
                                <input class="form-control" type="text" name="id" id="id" value="${videoEdit.id}" required readonly>

                                <label class="form-label" for="title">Title</label>
                                <input class="form-control" type="text" name="title" id="title" value="${videoEdit.title}" required>

                                <label class="form-label" for="views">Views</label>
                                <input class="form-control mb-3" type="number" name="views" id="views" value="${videoEdit.views}" required>

                                <input type="checkbox" class="form-check-input" name="active" id="active" ${videoEdit.active?"checked":""}>
                                <label class="form-check-label" for="active">Active</label>
                            </div>
                            <div class="col-12 mb-3">
                                <label class="form-label" for="description">Description</label>
                                <textarea class="form-control" id="description" rows="5"></textarea>
                            </div>
                            <div class="col-12 d-flex gap-2 justify-content-end">
                                <form method="post">
                                    <button formaction="${url}create" type="submit" class="btn btn-primary">Create</button>
                                    <button formaction="${url}update/${videoEdit.id}" type="submit" ${videoEdit == null?"disabled":""} class="btn btn-success">Update</button>
                                    <button formaction="${url}delete/${videoEdit.id}" type="submit" ${videoEdit == null?"disabled":""} class="btn btn-danger">Delete</button>
                                    <a href="${url}edit?video" class="btn btn-warning">Reset</a>
                                </form>
                            </div>
                        </div>
                    </form>
                </div>
                <div id="listVideos" class="container tab-pane fade ${param.videoPageNumber != null?"show":""}">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <td>Id</td>
                            <td>Title</td>
                            <td>Views</td>
                            <td>Status</td>
                            <td>Action</td>
                        </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${listVideos}" var="videoEach">
                                <tr>
                                    <td>${videoEach.id}</td>
                                    <td>${videoEach.title}</td>
                                    <td>${videoEach.views}</td>
                                    <td>${videoEach.active?"Active":"Inactive"}</td>
                                    <td><a href="${url}edit?video=${videoEach.id}">Edit</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="row">
                        <div class="col-2">
                            <span class="text-secondary">N videos</span>
                        </div>

                        <div class="col-10 d-flex gap-2 justify-content-end">
                            <a role="button" class="btn btn-secondary" href="?page=${0}"><i class="fas fa-fast-backward"></i></a>
                            <a role="button" class="btn btn-secondary" href="?page=${(pageVideoNumber-1 < 0)?pageVideoNumber:pageVideoNumber-1}"><i class="fa-solid fa-left-long"></i></a>
                            <a role="button" class="btn btn-secondary" href="?page=${(pageVideoNumber+1) > endVideoPage?pageVideoNumber:pageVideoNumber+1}"><i class="fa-solid fa-right-long"></i></a>
                            <a role="button" class="btn btn-secondary" href="?page=${endVideoPage}"><i class="fas fa-fast-forward"></i></a>
                        </div>
                    </div>

                </div>
            </div>

        </div>
    </div>

<%--    <!-- Users -->--%>
<%--    <div class="accordion-collapse collapse my-4 fade ${param.user!=null?"show":""}" id="users" data-bs-parent="#mainGroup">--%>
<%--        <div class="container">--%>
<%--            <!-- Nav tabs -->--%>
<%--            <ul class="nav nav-tabs" role="tablist">--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link active" data-bs-toggle="tab" href="#editUser">Edit User</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" data-bs-toggle="tab" href="#listUsers">List Users</a>--%>
<%--                </li>--%>

<%--            </ul>--%>
<%--            <!-- Tab panes -->--%>
<%--            <div class="tab-content">--%>
<%--                <div id="editUser" class="container w-75 tab-pane active">--%>
<%--                    <form action="" method="post" class="border rounded-2 p-3 was-validated">--%>
<%--                        <div class="row">--%>
<%--                            <div class="col-6">--%>
<%--                                <label class="form-label" for="username">Username</label>--%>
<%--                                <input class="form-control" type="text" name="username" id="username" required>--%>

<%--                                <label class="form-label" for="password">Password</label>--%>
<%--                                <input class="form-control" type="password" name="password" id="password" required>--%>
<%--                            </div>--%>

<%--                            <div class="col-6 mb-3">--%>
<%--                                <label class="form-label" for="fullname">Fullname</label>--%>
<%--                                <input class="form-control" type="text" name="fullname" id="fullname" required>--%>

<%--                                <label class="form-label" for="email">Email</label>--%>
<%--                                <input class="form-control" type="email" name="email" id="email" required>--%>
<%--                            </div>--%>

<%--                            <div class="col-12 d-flex gap-2 justify-content-end">--%>
<%--                                <button type="submit" class="btn btn-success">Update</button>--%>
<%--                                <button type="submit" class="btn btn-danger">Delete</button>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--                <div id="listUsers" class="container tab-pane fade">--%>
<%--                    <table class="table table-striped table-hover">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <td>Username</td>--%>
<%--                            <td>Password</td>--%>
<%--                            <td>Fullname</td>--%>
<%--                            <td>Email</td>--%>
<%--                            <td>Role</td>--%>
<%--                            <td>Action</td>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>

<%--                        <tbody>--%>
<%--                        <c:forEach items="${listUsers}" var="user">--%>
<%--                            <tr>--%>
<%--                                <td>${user.username}</td>--%>
<%--                                <td>${user.password}</td>--%>
<%--                                <td>${user.fullname}</td>--%>
<%--                                <td>${user.email}</td>--%>
<%--                                <td>${user.role?"Admin":"User"}</td>--%>
<%--                                <td><a href="${url}edit?user=${user.id}">Edit</a></td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                        </tbody>--%>
<%--                    </table>--%>

<%--                    <div class="row">--%>
<%--                        <div class="col-2">--%>
<%--                            <span class="text-secondary">N users</span>--%>
<%--                        </div>--%>

<%--                        <div class="col-10 d-flex gap-2 justify-content-end">--%>
<%--                            <button class="btn btn-secondary"><i class="fas fa-fast-backward"></i></button>--%>
<%--                            <button class="btn btn-secondary"><i class="fa-solid fa-left-long"></i></button>--%>
<%--                            <button class="btn btn-secondary"><i class="fa-solid fa-right-long"></i></button>--%>
<%--                            <button class="btn btn-secondary"><i class="fas fa-fast-forward"></i></button>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                </div>--%>
<%--            </div>--%>

<%--        </div>--%>
<%--    </div>--%>

<%--    <!-- Reports -->--%>
<%--    <div class="accordion-collapse collapse my-4 fade" id="reports" data-bs-parent="#mainGroup">--%>
<%--        <div class="container">--%>
<%--            <!-- Nav tabs -->--%>
<%--            <ul class="nav nav-tabs" role="tablist">--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link active" data-bs-toggle="tab" href="#listLikedVideos">List liked videos</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" data-bs-toggle="tab" href="#listUsersLiked">List Users liked by video</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" data-bs-toggle="tab" href="#listUsersShared">List Users shared by video</a>--%>
<%--                </li>--%>

<%--            </ul>--%>
<%--            <!-- Tab panes -->--%>
<%--            <div class="tab-content">--%>
<%--                <div id="listLikedVideos" class="container tab-pane active">--%>
<%--                    <table class="table table-striped table-hover">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <td>Title</td>--%>
<%--                            <td>Liked count</td>--%>
<%--                            <td>Latest like</td>--%>
<%--                            <td>Oldest like</td>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>

<%--                        <tbody>--%>
<%--                        <tr>--%>
<%--                            <td>123</td>--%>
<%--                            <td>123</td>--%>
<%--                            <td>123</td>--%>
<%--                            <td>123</td>--%>
<%--                        </tr>--%>
<%--                        </tbody>--%>
<%--                    </table>--%>
<%--                </div>--%>
<%--                <div id="listUsersLiked" class="container tab-pane fade">--%>
<%--                    <select class="form-select mt-3" aria-label="Default select example">--%>
<%--                        <option selected>Open this select menu</option>--%>
<%--                        <option value="1">One</option>--%>
<%--                        <option value="2">Two</option>--%>
<%--                        <option value="3">Three</option>--%>
<%--                    </select>--%>

<%--                    <table class="table table-striped table-hover">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <td>Username</td>--%>
<%--                            <td>Fullname</td>--%>
<%--                            <td>Email</td>--%>
<%--                            <td>Liked date</td>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>

<%--                        <tbody>--%>
<%--                        <tr>--%>
<%--                            <td>123</td>--%>
<%--                            <td>123</td>--%>
<%--                            <td>123</td>--%>
<%--                            <td>123</td>--%>
<%--                        </tr>--%>
<%--                        </tbody>--%>
<%--                    </table>--%>

<%--                </div>--%>

<%--                <div id="listUsersShared" class="container tab-pane fade">--%>
<%--                    <select class="form-select mt-3" aria-label="Default select example">--%>
<%--                        <option selected>Open this select menu</option>--%>
<%--                        <option value="1">One</option>--%>
<%--                        <option value="2">Two</option>--%>
<%--                        <option value="3">Three</option>--%>
<%--                    </select>--%>

<%--                    <table class="table table-striped table-hover">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <td>Username</td>--%>
<%--                            <td>Fullname</td>--%>
<%--                            <td>Email</td>--%>
<%--                            <td>Liked date</td>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>

<%--                        <tbody>--%>
<%--                        <tr>--%>
<%--                            <td>123</td>--%>
<%--                            <td>123</td>--%>
<%--                            <td>123</td>--%>
<%--                            <td>123</td>--%>
<%--                        </tr>--%>
<%--                        </tbody>--%>
<%--                    </table>--%>

<%--                </div>--%>
<%--            </div>--%>

<%--        </div>--%>
<%--    </div>--%>

</div>

</body>
</html>
