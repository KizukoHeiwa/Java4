<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05/11/2024
  Time: 11:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FPT Polytechnic</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<c:set var="url" value="/user/crud"/>
<body>
    <div class="container-fluid" style="width: 40%">
        <form method="post" class="was-validated">
            <div class="row">
                <div class="col-6">
                    <label class="form-label">Id:</label>
                    <input type="text" name="id" required class="form-control" value="${user.id}"><br>
                </div>
                <div class="col-6">
                    <label class="form-label">Password:</label>
                    <input type="password" name="password" required class="form-control" value="${user.password}"><br>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <label class="form-label">Fullname:</label>
                    <input type="text" name="fullname" required class="form-control" value="${user.fullname}"><br>
                </div>
                <div class="col-6">
                    <label class="form-label">Email Address:</label>
                    <input type="email" name="email" required class="form-control" value="${user.email}"><br>
                </div>
            </div>
            <div class="form-radio mb-3">
                <label class="form-label">Role:</label><br>
                <input type="radio" name="admin" class="form-check-input" value="true" ${user.admin?'checked':''}>
                <label class="form-check-label">Admin</label>
                <input type="radio" name="admin" class="form-check-input" value="false" ${!user.admin?'checked':''}>
                <label class="form-check-label">User</label>
            </div>
            <div class="text-danger">${message}</div>
            <button type="submit" formaction="${url}/create" class="btn btn-primary">Create</button>
            <button type="submit" formaction="${url}/update" class="btn btn-success">Update</button>
            <button type="submit" formaction="${url}/delete" class="btn btn-danger">Delete</button>
            <button type="submit" formaction="${url}/reset" class="btn btn-warning" formnovalidate>Reset</button>
        </form>
        <form method="post">
            <div class="row">
                <div class="col-6 d-inline-flex align-items-center">
                    <label class="form-label">Search:</label>
                    <input type="text" name="search" class="form-control" value="${param.search}">
                </div>
                <div class="col-4 d-inline-flex align-items-center">
                    <label class="form-label">Role:</label>
                    <input type="radio" name="searchRole" class="form-check-input" value="true" ${param.searchRole?'checked':''}>
                    <label class="form-check-label">Admin</label>
                    <input type="radio" name="searchRole" class="form-check-input" value="false" ${!param.searchRole?'checked':''}>
                    <label class="form-check-label">User</label>
                </div>
                <div class="col-2 d-inline-flex align-items-center">
                    <button type="submit" formaction="${url}/search" class="btn btn-primary">Search</button>
                </div>
            </div>
        </form>
    </div>

    <div class="container-fluid" style="width: 50%">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Password</th>
                <th>Fullname</th>
                <th>Email</th>
                <th>Role</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.password}</td>
                    <td>${u.fullname}</td>
                    <td>${u.email}</td>
                    <td>${u.admin?'Admin':'User'}</td>
                    <td><a href="${url}/edit/${u.id}">Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
