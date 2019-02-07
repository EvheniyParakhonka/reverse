<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" type="text/css" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>

    <style>
        html, body {
            height:100%;
        }

        .center {
            border: 2px solid whitesmoke;
            margin: auto;
            position: relative;
            text-align: center;
            top: 20%;
            width: 20%;
        }
    </style>
</head>

<body>
<div class="center">
    <p style="color: snow">Login page</p>
    <form action="./j_spring_security_check" method="post">
        <div style="color: whitesmoke"><label> User Name : <input style="color: black" type="text" name="j_username"/> </label></div>
        <div style="color: whitesmoke"><label> Password: <input style="color: black" type="password" name="j_password"/> </label></div>
        <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
        <div><input class="btn " type="submit" value="Sign In"/></div>
    </form>
    <a href="./registration" style="color: whitesmoke">Add new user</a>
</div>
</body>
</html>