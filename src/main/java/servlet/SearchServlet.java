package servlet;

import entity.Article;
import exception.BusinessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.Implement.ArticleService;
import service.Implement.CommentService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // The article id you want to get
            String arg = request.getParameter("arg");
            ArticleService as = new ArticleService();

            List<Article> list = as.getArticlesSearch(arg);

            request.setAttribute("size", list.size());
            request.setAttribute("articles", list);

            request.getRequestDispatcher("/view/search_list.jsp").forward(request, response);

        } catch (BusinessException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
