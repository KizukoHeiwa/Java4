<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/11/2024
  Time: 09:16 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<head>
    <title>FPT Polytechnic</title>
</head>
<body>
    <c:url value="/test" var="url"/>
    <div class="container text-center">
        <form method="get" class="w-25 mx-auto d-flex">
            <input type="text" name="search" class="form-control" placeholder="Search...">
            <button class="btn btn-success" formaction="${url}">Search</button>
        </form>
        <div class="bai3">
            <h1>${userFavs[0].userid.fullname}</h1>
                <table class="table table-hover table-primary">
                    <tbody>
                    <c:forEach var="userFav" items="${userFavs}">
                        <tr>
                            <td>${userFav.videoid.title}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
        </div>

        <h1>List liked video</h1>
        <div class="bai4">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <td>Video title</td>
                        <td>Người thích</td>
                        <td>Ngày thích</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="fav" items="${listFav}">
                    <tr>
                        <td>${fav.videoid.title}</td>
                        <td>${fav.userid.fullname}</td>
                        <td>${fav.likedate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <form method="get" class="w-25 mx-auto d-flex">
            <input type="text" name="keyword" class="form-control" placeholder="Search video">
            <button class="btn btn-success" formaction="${url}">Search</button>
        </form>
        <div class="lab41">
            <h1>Search Video by title</h1>
            <table class="table table-hover table-secondary">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Title</td>
                        <td>Poster</td>
                        <td>Views</td>
                        <td>Description</td>
                        <td>Active</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="video" items="${listVideo}">
                    <tr>
                        <td>${video.id}</td>
                        <td>${video.title}</td>
                        <td>${video.poster}</td>
                        <td>${video.views}</td>
                        <td>${video.description}</td>
                        <td>${video.active}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="lab41">
            <h1>Top 10 Fav Video</h1>
            <table class="table table-hover table-success">
                <thead>
                <tr>
                    <td>Id</td>
                    <td>Title</td>
                    <td>Poster</td>
                    <td>Views</td>
                    <td>Description</td>
                    <td>Active</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="video" items="${listFavVideo}">
                    <tr>
                        <td>${video.id}</td>
                        <td>${video.title}</td>
                        <td>${video.poster}</td>
                        <td>${video.views}</td>
                        <td>${video.description}</td>
                        <td>${video.active}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</body>
</html>
