package servlet;

import entity.Article;
import entity.Comment;
import entity.User;
import exception.BusinessException;
import exception.FailException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.Implement.CommentService;
import service.Implement.UserService;
import utils.DateUtils;

import java.io.IOException;
import java.util.Date;

@WebServlet("/NewComment")
public class NewCommentServlet extends HttpServlet {
    private static final String LIST = "/ArticleServlet";

    private final CommentService commentService;

    public NewCommentServlet() {
        super();
        commentService = new CommentService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            long commentId = Long.parseLong(request.getParameter("commentId"));
            commentService.deleteComment(commentId);
            request.getRequestDispatcher("/ArticleServlet").forward(request, response);

        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long addedUser;
            String name;
            UserService userService = new UserService();
            User user = null;

            user = userService.getUserByEmail(request.getParameter("email"));

            if (user != null) {
//                request.setAttribute("name", user.getUserName());
                addedUser = user.getId();
                name = user.getUserName();
            } else {
                User user1 = new User();
                user1.setUserName(request.getParameter("name"));
                user1.setEmail(request.getParameter("email"));
                addedUser = userService.addUser(user1);
                name = user1.getUserName();
            }
            Comment comment = new Comment();
            comment.setIdArticle(Long.valueOf(request.getParameter("id")));
            comment.setIdUser(addedUser);
            comment.setNickname(name);
            comment.setContent(request.getParameter("content"));
            commentService.addComment(comment);
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("/ArticleServlet").forward(request, response);
    }

}