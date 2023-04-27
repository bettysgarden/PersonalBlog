<%--
  Created by IntelliJ IDEA.
  User: Elizaveta1
  Date: 24.04.2023
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>

<div class="head_line"></div>
<div class="container" id="main">
    <div class="head">
        <div id="title">
            <h2>
                <a href="../index.jsp">MyBlog</a>
            </h2>
        </div>
    </div>

    <div class="jumbotron text-center">
        <h1>Search Tab</h1>
    </div>
    <div class="line"></div>
    <%--  TODO форма для ввода слова для поиска--%>
    <form action="SearchServlet" method="post">
        <label><h4>Type a word</h4></label>
        <input style="width:30%" class="form-control" type="text" name="arg">
        <br/>
        <input class="btn btn-primary" type="submit" value="Search" onclick="onclick">
    </form>

</body>
</html>
