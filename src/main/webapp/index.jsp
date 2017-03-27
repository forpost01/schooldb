<html>
<head>
  <%@ page language="java" contentType="text/html;charset=utf-8"%>
  <title>Simple School Manager</title>
</head>
<body>
<p><a href="/rest/schools/all">List of schools</a>


<p><a href="/rest/class">list of class</a>
<p><a href="/rest/class/1">get class 1</a>

<p><a href="/index2.jsp">Добавить школу</a>



  <form action="/rest/schools" method="get">
<p><b>Введите ID школы для отображения</b></p>
<p><input name="id"></p>
<p><input type="submit" value="Получить"></p>
</form>

<form action="/rest/schools/xml" method="get">
  <p><b>Введите ID школы для получения XML</b></p>
  <p><input name="id"></p>
  <p><input type="submit" value="Получить"></p>
</form>

</body>
</html>
