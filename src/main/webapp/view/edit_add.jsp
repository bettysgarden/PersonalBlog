<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="../../css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Add new article</title>
    <style>
        input.text {
            width:  800px;
            height: 400px;
            padding: 5px 10px 5px 10px;
            border:1px solid #999;
            font-size:16px;
            font-family: Tahoma;
        }
    </style>
</head>
<body>

<form method="POST" action='CommandsServlet' name="formAddArticle" >
    Title : <input class="text"
        type="text" name="title"
        value="<c:out value="${article.title}" />"/>
    <br/>
    Content : <input  class="text"
        type="text" name="content"
        value="<c:out value="${article.content}" />"/>
    <br/>
    <input
            type="submit" value="Submit"/>
</form>
<div id="footer">

    <div class="r_div">
        <a href="#"><input class="btn btn-default" value="Back to top" style="width:50%;"/></a>
    </div>

</div><!-- footer -->
</body>
</html>