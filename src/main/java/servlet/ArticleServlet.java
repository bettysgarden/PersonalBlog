package servlet;

import entity.Article;
import exception.BusinessException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.Implement.ArticleService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/")
public class ArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "./article.jsp";
    private static String LIST_ARTICLE = "./listArticles.jsp";
    private final ArticleService as;

    public ArticleServlet() {
        super();
        as = new ArticleService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        try {
            if (action.equalsIgnoreCase("delete")) {
                long articleId = Long.parseLong(request.getParameter("id"));
                as.deleteArticle(articleId);
                forward = LIST_ARTICLE;
                request.setAttribute("articles", as.getArticles());
            } else if (action.equalsIgnoreCase("edit")) {
                forward = INSERT_OR_EDIT;
                long articleId = Long.parseLong(request.getParameter("id"));
                Article article = as.getArticleById(articleId);
                request.setAttribute("article", article);
            } else if (action.equalsIgnoreCase("listArticles")) {
                forward = LIST_ARTICLE;
                request.setAttribute("articles", as.getArticles());
            } else {
                forward = INSERT_OR_EDIT;
            }
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        try {
            String articleid = request.getParameter("Articleid");
            if (articleid == null || articleid.isEmpty()) {

                as.addArticle(request);


            } else {
                article.setId(Long.parseLong(articleid));
                as.updateArticle(request);
            }
            RequestDispatcher view = request.getRequestDispatcher(LIST_ARTICLE);
            request.setAttribute("articles", as.getArticles());
            view.forward(request, response);
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }

    }
}
