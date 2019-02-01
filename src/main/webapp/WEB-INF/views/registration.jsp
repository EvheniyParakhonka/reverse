<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Registration Form</title>
	<%--<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>--%>
	<%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>--%>
</head>
<body>
Add new user
{{#message}}
{{message}}
{{/message}}
<form :form action="./registration" method="post" >
	<div><label> User Name : <input type="text" name="username" id="userName"/> </label></div>
	<div><label> Password: <input type="password" name="password" id="userPassword"/> </label></div>
	<input type="hidden" name="_csrf" value="{{_csrf.token}}" />
	<div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>