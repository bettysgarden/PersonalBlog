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
import java.util.List;

@WebServlet("/")
public class CommandsServlet extends HttpServlet {
    private static final String INSERT_OR_EDIT = "/view/edit_add.jsp";
    private static final String LIST_ARTICLE = "/view/list.jsp";
    private static final String SEARCH = "/view/search.jsp";
    private final ArticleService as;

    public CommandsServlet() {
        super();
        as = new ArticleService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String forward;
            String action = request.getParameter("action");

            if (action.equalsIgnoreCase("delete")) {
                long articleId = Long.parseLong(request.getParameter("id"));
                as.deleteArticle(articleId);
                forward = LIST_ARTICLE;

                List<Article> list = as.getArticles();
                request.setAttribute("size", list.size());
                request.setAttribute("articles", list);

            } else if (action.equalsIgnoreCase("edit")) {
                forward = INSERT_OR_EDIT;
                long articleId = Long.parseLong(request.getParameter("id"));
                Article article = as.getArticleById(articleId);
                request.setAttribute("article", article);

            } else if (action.equalsIgnoreCase("listArticles")) {
                forward = LIST_ARTICLE;
                List<Article> list = as.getArticles();

                request.setAttribute("size", list.size());
                request.setAttribute("articles", list);
            } else if (action.equalsIgnoreCase("search")) {
                forward = SEARCH;
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
            String articleId = request.getParameter("id");
            if (articleId == null || articleId.isEmpty()) {
                as.addArticle(article);
            } else {
                article.setId(Long.parseLong(articleId));
                as.updateArticle(article);
            }
            RequestDispatcher view = request.getRequestDispatcher(LIST_ARTICLE);
            request.setAttribute("articles", as.getArticles());
            view.forward(request, response);
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }

    }
}
