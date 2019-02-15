<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue-resource/1.5.0/vue-resource.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <script>

    </script>

    <style>
        .list-group-item:hover {
            z-index: auto;
        }

        .list {
            position: absolute;
            bottom: 20px;
            padding-left: 15px;
            padding-right: 15px;
            height: 90px;
        }

        .clip {
            white-space: nowrap;
            overflow: hidden;
        }
    </style>

    <title>Title</title>

</head>

<body style="background-color: #F5F9FF">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark justify-content-between">
    <img src="https://www.ydop.com/wp-content/uploads/2015/06/json-logo-300x143.png" class="rounded" width="60px"
         height="30px"
         alt="logo">
    <div id="spinner" class="text-center" v-if="displ" style="padding-left:65px;">
        <div class="spinner-border text-primary" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <div class=" text-end">
        <sec:authorize access="isAuthenticated()">
            <p6 style="color: whitesmoke;padding-right: 10px">Ваш логин: <sec:authentication
                    property="principal.username"/></p6>
            <button type="button" class="btn btn-outline-primary btn-sm "
                    :href="<c:url value="/logout" />">Выйти
            </button>

        </sec:authorize>
    </div>

</nav>

<div class="container-fluid " style="height: 70%">
    <div class="row" style="height: 10%;">
        <div class="col-3 justify-content-start align-self-end">
            <p5>JSON</p5>
        </div>
        <div id="buttonJson" class="col-3 text-right align-self-end">
            <button class="btn btn-sm btn-secondary " v-on:click="jsonToXml" :disabled="disable">&gt;&gt;</button>
        </div>
        <div id="buttonXml" class="col-3 justify-content-start align-self-end">
            <button class="btn btn-sm btn-secondary" v-on:click="xmlToJson" :disabled="disable">&lt;&lt;</button>
        </div>
        <div class="col-3 text-right align-self-end">
            <p5>XML</p5>
        </div>
    </div>
    <div class="row">
        <div id="areaJson" class="col-6" style=" padding-top: 10px">
        <textarea class="col-12 rounded " :disabled="disable" v-model="message" placeholder="вставте JSON"
                  style="height: 90%; "></textarea>
        </div>
        <div id="areaXml" class="col-6" style=" padding-top: 10px">
        <textarea class="col-12 rounded" :disabled="disable" v-model="message" placeholder="вставте xml"
                  style="height: 90%; "></textarea>
        </div>
    </div>
    <div class="row justify-content-end">
        <div class="form-check" style="padding-right: 15px">
            <input type="checkbox" class="form-check-input" id="checkbox" v-model="checked" >
            <label class="form-check-label" for="checkbox">Save history</label>
        </div>
    </div>
</div>

<div class="container-fluid list">
    <div id="v-for-object" class="editable rounded list-group-item:hover{ z-index: auto; }" style="padding: 0px">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top " style="
        padding: 0px; color: whitesmoke">

            <div class="col-2">user</div>
            <div class="col-2">date</div>
            <div class="col-3">json</div>
            <div class="col-1">more</div>
            <div class="col-3">xml</div>
            <div class="col-1">more</div>

        </nav>
        <div :key="item.id" v-for="(item, index) in items" class="list-group-item ">

            <div class="row" style="padding-bottom: 0px">
                <div class="col-2">{{item.user}}</div>
                <div class="col-2">{{item.date}}</div>
                <div class="col-3 clip">{{item.jsonFull}}</div>
                <div class="col-1">

                    <a class="btn btn-link" v-on:click="getFullJsonFromHistory(index)"
                       style="padding: 0px; margin: 0px">
                        ...
                    </a>

                </div>
                <div class="col-3 clip">{{item.xmlFull}}</div>
                <div class="col-1">
                    <a class="btn btn-link" v-on:click="getFullXmlFromHistory(index)" style="padding: 0px; margin: 0px">
                        ...
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="container-fluid navbar-fixed-bottom" id="page">
        <div aria-label="Page navigation example">
            <ul class="pagination justify-content-center">

                <li v-for="item in items" v-on:click="getPageFunction(item.page)" class="page-item">
                    <a class="page-link">{{item.page}}</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">JSON</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLongTitle2" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle2">XML</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/static/js/main.js"/>"></script>
<script>

</script>
</body>
</html>
