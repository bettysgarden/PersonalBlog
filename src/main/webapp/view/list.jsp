<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All Articles</title>
    <style>
        table, th, td {
            border: 1px solid black;
            /*width:1200px;*/
        }

        table.center {
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
<div class="jumbotron text-center">
    <h1>My Blog</h1>
</div>
<div class="container">
    <br class="row">
    <c:choose>
        <c:when test="${size == 0}">
            <div style="text-align: center;">
                <h5>No articles yet.</h5>
            </div>
            <div class="line"></div>
        </c:when>

        <c:otherwise>
            <table class="center">
                <thead>
                <tr>
                    <th style="width: 50px; text-align: center">Id</th>
                    <th style="width: 200px; text-align: center">Title</th>
                    <th style="width: 600px; text-align: center">Content</th>
                    <th style="width: 100px; text-align: center">Time Added</th>
                    <th style="width: 80px; text-align: center">Comment Count</th>
                    <th style="width: 100px; text-align: center" colspan=2>Action</th>
                </tr>
                </thead>
                <tbody>
                    <%--@elvariable id="articles" type="java.util.List"--%>
                <c:forEach items="${articles}" var="article">
                    <tr>
                        <td style="text-align: center"><c:out value="${article.id}"/></td>
                            <%--            <td><c:out value="${article.title}"/></td>--%>

                        <td><a href="ArticleServlet?id=<c:out value="${article.id}"/>"><c:out
                                value="${article.title}"/></a></td>
                        <td><c:out value="${article.content}"/></td>
                        <td style="text-align: center"><c:out value="${article.time}"/></td>
                        <td style="text-align: center"><c:out value="${article.commentCount}"/></td>
                        <td style="text-align: center"><a
                                href="CommandsServlet?action=edit&id=<c:out value="${article.id}"/>">Update</a></td>
                        <td style="text-align: center"><a
                                href="CommandsServlet?action=delete&id=<c:out value="${article.id}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </br>
            <button type="button" class="btn btn-secondary btn-lg btn-block"><a
                    href="<c:url value="CommandsServlet?action=search"/>">Search</a></button>
        </c:otherwise>
    </c:choose>
    <button type="button" class="btn btn-secondary btn-lg btn-block"><a
            href="<c:url value="CommandsServlet?action=insert"/>">Add Article</a></button>
</div>
<div id="footer">
    <a href="./index.jsp">MyBlog Home&nbsp;&nbsp;</a>|
    <a href="#">&nbsp;&nbsp;Back to top</a>
</div>
</body>
</html>