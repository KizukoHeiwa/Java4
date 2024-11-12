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
    <div class="container">
        <h1>${user.fullname}</h1>
        <c:forEach var="video" items="${list}"><h3>${video.title}</h3></c:forEach>
        <table class="table table-striped">
            <thead>
                <tr>
                    <td>Title</td>
                    <td>Người thích</td>
                    <td>Ngày thích</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="video" items="${list}">
                <tr>
                    <td>${video.title}</td>
                    <td>${video.userFavorite}</td>
                    <td>${video.dateFavorite}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
