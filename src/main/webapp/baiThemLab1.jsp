<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/11/2024
  Time: 5:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FPT Polytechnic</title>
</head>
<style>
    table, th, td {
        border: 1px solid black;
        padding: 8px;
        text-align: center;
    }
</style>
<body>
    <c:set var="pageNumber" value="${pageNumber}" scope="request"/>
    <table>
        <tr>
            <th>ID</th>
            <th>Password</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>Admin</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <th>${user.id}</th>
                <th>${user.password}</th>
                <th>${user.fullname}</th>
                <th>${user.email}</th>
                <th>${user.admin}</th>
            </tr>
        </c:forEach>
    </table>
    <form method="post">
        <button formaction="?page=${0}"><<</button>
        <button formaction="?page=${(pageNumber-1 < 0)?pageNumber:pageNumber-1}"><-</button>
        <button formaction="?page=${(pageNumber+1) > endPage?pageNumber:pageNumber+1}">-></button>
        <button formaction="?page=${endPage}">>></button>
    </form>
</body>
</html>
