package service.Implement;

import dao.Interface.ArticleDao;
import dao.Implement.ArticleDaoImpl;
import entity.Article;
import exception.BusinessException;
import exception.DaoException;
import exception.FailException;
import jakarta.servlet.http.HttpServletRequest;
import service.Interface.ArticleServiceInterface;
import utils.ArticleUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class ArticleService implements ArticleServiceInterface {

    private final ArticleDao articleDao;

    public ArticleService() {
        articleDao = new ArticleDaoImpl();
    }

//
//    public List<entity.Article> getArticle(String column, String value) {
//        return articleDao.getArticleByColumn(column, value);
//    }


    /**
     * Get previous article
     *
     * @param time
     * @return
     */
    @Override
    public Article getPreviousArticle(String time) throws BusinessException {
        try {
            return articleDao.getANearArticle(time, articleDao.LESS);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }

    /**
     * Get the next article
     *
     * @param time
     * @return
     */
    @Override
    public Article getNextArticle(String time) throws BusinessException {
        try {
            return articleDao.getANearArticle(time, articleDao.MORE);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }

    /**
     * Get all articles, intercept the length of the article, intercept the time, remove the hour, minute, second
     *
     * @return
     */
    @Override
    public List<Article> getArticles() throws BusinessException {
        try {
            List<Article> list = articleDao.getAllArticles();
            for (Article a : list) {
                ArticleUtils.cutContent(list);
                ArticleUtils.cutTime(list);
            }
            return list;
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public Article getArticleById(long id) throws BusinessException {
        try {
            return articleDao.getArticleById(id);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }


    @Override
    public long addArticle(Article article) throws BusinessException {
        try {
            return articleDao.addArticle(article);

        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }

    }

    @Override
    public void updateArticle(Article article) throws BusinessException {
        try {
            articleDao.updateArticle(article);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public void deleteArticle(long id) throws BusinessException {
        try {
            articleDao.deleteArticle(id);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }
}
