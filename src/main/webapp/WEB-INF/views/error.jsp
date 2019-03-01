<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<% if (response.getStatus() == 500) { %>
<font color="red">Error: <%=response.getStatus() %>
</font><br>

include login page
<%@ include file="vue.jsp" %>
<%} else {%>
Hi There, error code is <%=request.getAttribute("message") %><br>
Please go to <a href="vue.jsp">home page</a>
<%} %>
</body>
</html>
