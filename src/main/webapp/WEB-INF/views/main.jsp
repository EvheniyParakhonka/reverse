<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue-resource/1.5.0/vue-resource.js"></script>

    <style>
        .area {
            position: absolute;
            left: 25px;
            top: 140px;
        }

        .outer {
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
    <%--<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>--%>
    <%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>--%>
    <title>Title</title>

</head>

<body>
<div>
    <sec:authorize access="isAuthenticated()">
        <p>Ваш логин: <sec:authentication property="principal.username" /></p>
        <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>

    </sec:authorize>
</div>
<div class="area" style="width: 95%">
    <div id="areaJson">
        <textarea v-model="message" placeholder="вставте JSON"
                    style="height: 362px;float: left; width: 40%"></textarea>
    </div>
    <div id="buttons" class="outer" style="float: left; width: 15%; height: 362px;  ">
        <button v-on:click="jsonToXml"> json &gt;&gt; xml</button>
        <button v-on:click="xmlToJson"> json &lt;&lt; xml</button>


    </div>
    <div id="areaXml">
        <textarea v-model="message" placeholder="вставте xml"
                    style="height: 362px; float: left; width: 40%"></textarea>
    </div>
</div>

<textarea v-model="story" readonly="readonly" style="width: 90%; position: absolute;
bottom: 20px; left: 25px; height: 90px" placeholder="ваша история пуста"></textarea>

<script src="<c:url value="/static/js/main.js"/>">
</script>
</body>
</html>
