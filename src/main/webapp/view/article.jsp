<%--
  Created by IntelliJ IDEA.
  User: Elizaveta1
  Date: 24.04.2023
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${article.title} | MyBlog</title>
    <!-- Custom styles for this template -->
    <style>
        body {
            background: #eee;
        }

        a:link {
            text-decoration: none;
        }

        .c_left {
            text-align: left;
        }

        .head_line {
            border-bottom: 1px solid #177cb0;
        }

        .line {
            clear: both;
            border-top: 1px solid #C0C0C0;
            margin-bottom: 20px;
        }

        #article {
            margin-top: 80px;
            margin-bottom: 80px;
            text-align: center;
            clear: both;
        }

        #a_head {
            padding-bottom: 40px;
        }

        #a_content {
            margin-top: 50px;
            margin-bottom: 50px;
            text-align: center;
            padding: 5px;
            background-color: lightgray;
        }

        #footer {
            margin-top: 100px;
            text-align: center;
            margin-bottom: 20px;
        }

        .comment {
            margin-top: 30px;
            margin-bottom: 30px;
            padding: 20px;
            clear: both;
        }

        #c_content {
            overflow: hidden;
            text-align: left;
            clear: both;
            margin-left: 60px;
            margin-right: 60px;
            margin-top: 10px;
            margin-bottom: 10px;
        }
    </style>

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

    <div id="article">

        <div class="jumbotron text-center">
            <h1>${article.title}</h1>
        </div>
    </div>
    <div class="line"></div>

    <div id="a_content">
        <textarea id="article_content" cols="150" rows="70" readonly>
            ${article.content}
        </textarea>
    </div>

    <div class="line"></div>
    <!-- comment -->
    <div class="comment">
        <div>
            <h3>Comments</h3>
        </div>
        <!-- Load article comments -->
        <c:if test="${comment!=null}">
            <c:forEach var="comm" varStatus="status" items="${comment}">

                <div class="row">
                    <table>
                        <tbody>
                        <tr>
                        <div class="f_div">
                            <td style="text-align: right;width: 600px">
                                <span style="color: #428bca"> ${comm.nickname}&nbsp;</span>
                                </td>
                            <td style="text-align: right;width: 200px">
                                <span>&nbsp;&nbsp;${comm.time}</span>
                            </td>
                            <td style="text-align: right; width: 100px">
                                <a href="NewCommentServlet?commentId=<c:out value="${comm.id}&id=${article.id}"/>">Delete</a>
                            </td>
                        </div>
                        </tr>
                        </tbody>
                    </table>
                    <div id="c_content" class="c_left">
                        <pre>${comm.content}</pre>
                    </div>
                    <div class="line"></div>
                </div>


            </c:forEach>
        </c:if>
        <%--        <p>No comments yet. Be the first.</p>--%>
    </div>
    <!-- Subcomments can be extended here -->

    <!-- Write a comment -->
    <div id="comment">
        <div>
            <h3>Leave a comment</h3>
        </div>
        <form action="NewCommentServlet?id=${article.id}" method="post">
            Name : <input style="width:30%" class="form-control" type="text" name="name">
            <br/>
            Email : <input style="width:30%" class="form-control" type="text" name="email" placeholder="name@example.com">
            <br/>
            Content : <textarea style="resize:none; width:100%; height:180px;" name="content"></textarea>
            <br/>
            <br/>
<%--            <input class="btn btn-default" type="submit" value="comment" onclick="onclick"/>--%>
            <input class="btn btn-primary" type="submit" value="Comment" onclick="onclick">
            <br/>
            <br/>
        </form>
    </div>
    <!-- -->
    <div class="line"></div>

</div>

<div id="footer">
    <a href="../index.jsp">MyBlog Home&nbsp;&nbsp;</a>|
    <a href="#">&nbsp;&nbsp;Back to top</a>
</div>
</body>
</html>