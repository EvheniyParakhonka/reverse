<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>

    <style>
        html, body {
            height: 100%;
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
    <p style="color: whitesmoke">Add new user</p>

    <form :form action="./registration" method="post">
        <div style="color: whitesmoke"><label> User Name : <input style="color: black" type="text" name="username"
                                                                  id="userName"/> </label></div>
        <div style="color: whitesmoke"><label> Password: <input style="color: black" type="password" name="password"
                                                                id="userPassword"/> </label></div>
        <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
        <div><input type="submit" value="sign up"/></div>
    </form>
</div>
</body>
</html>