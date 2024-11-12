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
    <div class="container text-center">
        <div class="bai3">
            <h1>${userFavs[0].userid.fullname}</h1>
            <c:forEach var="userFav" items="${userFavs}"><h3>${userFav.videoid.title}</h3></c:forEach>
        </div>
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
    </div>
</body>
</html>
