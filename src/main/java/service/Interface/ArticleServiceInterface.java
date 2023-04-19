package service.Interface;

import entity.Article;
import exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ArticleServiceInterface {
    Article getPreviousArticle(String time) throws BusinessException;

    Article getNextArticle(String time) throws BusinessException;

    List<Article> getArticles() throws BusinessException;

    Article getArticleById(long id) throws BusinessException;

    Article addArticle(HttpServletRequest request) throws BusinessException;

    Article updateArticle(HttpServletRequest request) throws BusinessException;

    void deleteArticle(long id) throws BusinessException;
}
