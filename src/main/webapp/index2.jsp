<!DOCTYPE HTML>
<html>
<head>
    <%@ page language="java" contentType="text/html;charset=utf-8"%>
    <title>Java Servlet JSON</title>
    <script src="js/jquery.1.9.1.min.js"></script>

    <!-- bootstrap just to have good looking page -->
    <link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />

    <!-- we code these -->
    <script src="js/myfunctions.js"></script>
    <script src="js/jquery-3.2.0.min.js"></script>

</head>

<body>

<h1 style="text-align:center">Java Servlet Send & Receive JSON<br></h1>

<!-- article inputs -->
<div class="article" style="margin:10px;">
    <div class="input-prepend">
        <span class="add-on">Номер школы</span>
        <input class="span4" id="school_id" name="school_id" type="text" placeholder="123">
    </div>
    <br/>
    <div class="input-prepend">
        <span class="add-on">ФИО</span>
        <input class="span4" id="fioDirector" name="fioDirector" type="text" placeholder="Иванов...">
    </div>
    <br/>
    <div class="input-prepend">
        <span class="add-on">адрес</span>
        <input class="span4" id="address" name="address" type="text" placeholder="Парковая 1">
    </div>
    <br/>
    <div class="input-prepend">
        <span class="add-on">расчетный счет</span>
        <input class="span4" id="accountNumber" name="accountNumber" type="text" placeholder="240000597948">
    </div>
    <p>
        <button class="btn btn-primary" type="button" onclick="sendAjax()">Добавить</button>
    </p>
</div>

<button class="btn btn-primary" type="button" onclick="callServlet()">Показать</button>


<!-- display all articles -->
<div style="width:700px;padding:20px;S">
    <h5 style="text-align:center"><i style="color:#ccc"><small>Schools</small></i></h5>

    <table id="added-schools" class="table">
        <tr>
            <th>номер школы</th>
            <th>ФИО директора</th>
            <th>адрес</th>
            <th>расчетный счет</th>
        </tr>
    </table>
</div>
</body>
</html>