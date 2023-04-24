<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Add new article | MyBlog</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

    <form method="POST" action='CommandsServlet' name="formAddArticle">
        ID : <input style="width:30%" type="text" class="form-control" readonly="readonly" name="id"
                    value="<c:out value="${article.id}" />"/>
        <br/>
        <br/>
        Title : <input style="width:30%" class="form-control" type="text" name="title"
                       value="<c:out value="${article.title}" />"/>
        <br/>
        <br/>
        Content : <textarea style="resize:none; width:100%; height:600px;" class="form-control"
                            name="content"> ${article.content} </textarea>
        <br/>
        <br/>
        <input class="btn btn-primary" type="submit" value="Submit" onclick="onclick">
    </form>
    <div class="line"></div>
</div>
</body>
</html>