<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Articles</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Content</th>
        <th>Time</th>
        <th>Comment Count</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${articles}" var="article">
        <tr>
            <td><c:out value="${article.id}"/></td>
            <td><c:out value="${article.title}"/></td>
            <td><c:out value="${article.content}"/></td>
<%--            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${article.time}"/></td>--%>
            <td><c:out value="${article.time}"/></td>
            <td><c:out value="${article.commentCount}"/></td>
            <td><a href="ArticleServlet?action=edit&articleId=<c:out value="${article.id}"/>">Update</a></td>
            <td><a href="ArticleServlet?action=delete&articleId=<c:out value="${article.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="<c:url value="/ArticleServlet?action=insert"/>">Add Article</a></p>
</body>
</html>