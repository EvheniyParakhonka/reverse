<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login page</title>
		<%--<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>--%>
		<%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>--%>
		<%--<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />--%>
	</head>

	<body>
	Login page
	<form action="./j_spring_security_check" method="post">
		<div><label> User Name : <input type="text" name="j_username"/> </label></div>
		<div><label> Password: <input type="password" name="j_password"/> </label></div>
		<input type="hidden" name="_csrf" value="{{_csrf.token}}" />
		<div><input type="submit" value="Sign In"/></div>
	</form>
	<a href="./registration">Add new user</a>
	</body>
</html>