package dao.Interface;

import entity.Article;
import exception.DaoException;

import java.util.List;

public interface ArticleDao {
    static final int LESS = 1;
    static final int MORE = 2;

    Article getANearArticle(String time, int lessOrMore) throws DaoException;

    long addArticle(Article a) throws DaoException;

    void deleteArticle(long id) throws DaoException;

    List<Article> getAllArticles() throws DaoException;
    Article getArticleById(long id) throws DaoException;

    void updateArticle(Article article) throws DaoException;
}