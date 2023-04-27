package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entity.Article;
import exception.BusinessException;
import service.Implement.ArticleService;
import service.Implement.CommentService;

@WebServlet("/Article")
public class ArticleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // The article id you want to get
            String id = request. getParameter("id");
            long articleId = Long.parseLong(id);
            ArticleService as = new ArticleService();

            // article
            Article a = as.getArticleById(articleId);
            request.setAttribute("article", a);

            // load article comments
            CommentService cs = new CommentService();
            request.setAttribute("comment", cs.loadComments(articleId));

            request.getRequestDispatcher("/view/article.jsp").forward(request, response);

        } catch (BusinessException e) {
            e.printStackTrace();
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}