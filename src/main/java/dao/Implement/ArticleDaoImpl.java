package dao.Implement;

import dao.Interface.ArticleDao;
import entity.Article;
import exception.DaoException;
import utils.ConnectionFactory;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleDaoImpl implements ArticleDao {
    //    private static final Logger logger = LoggerFactory.getLogger(dao.Implement.ArticleDaoImpl.class);
    private static final Logger logger = Logger.getLogger(ArticleDaoImpl.class.getName());

    private static final String SELECT_LESS
            = "SELECT idarticle, title, time, comment, content FROM article WHERE TIME < ? ORDER BY time DESC";// TODO: 07.04.2023  use fields instead of *
    private static final String SELECT_MORE
            = "SELECT idarticle, title, time, comment, content FROM article WHERE TIME > ? ORDER BY time";
    private static final String SELECT_ALL
            = "SELECT idarticle, title, time, comment, content FROM article";
    private static final String SELECT
            = "SELECT idarticle, title, time, comment, content FROM article WHERE idarticle = ?";
    private static final String INSERT
            = "INSERT INTO article (title, content) VALUES (?, ?)";
    private static final String UPDATE
            = "UPDATE article SET content = ? WHERE title = ?";
    private static final String DELETE
            = "DELETE FROM article WHERE idarticle = ?";

    @Override
    public Article getANearArticle(String time, int lessOrMore) throws DaoException {
        Article article = null;
        try (Connection con = ConnectionFactory.getConnection()) {
            PreparedStatement pst = null;
            if (lessOrMore == this.LESS) {
                pst = con.prepareStatement(SELECT_LESS);
            } else if (lessOrMore == this.MORE) {
                pst = con.prepareStatement(SELECT_MORE);
            }

            ResultSet rs = null;
            if (pst != null) {
                rs = pst.executeQuery();
            }
            assert rs != null;
            if (rs.next()) {
                long articleId = rs.getLong("idArticle"); // TODO: 07.04.2023 null?
                article = new Article(articleId,// TODO: 07.04.2023 check not null
                        rs.getString("title"),
                        rs.getString("time"),
                        rs.getInt("comment"),
                        rs.getString("content"));
            }
            DBUtils.Close(pst, rs);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
        return article;
    }

    @Override
    public Long addArticle(Article article) throws DaoException {
        long articleId = -1L;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"idarticle"})) {
            pst.setString(1, article.getTitle());
            pst.setString(2, article.getContent());
            pst.executeUpdate();

            ResultSet gk = pst.getGeneratedKeys();

            if (gk.next()) {
                articleId = gk.getLong("idarticle");
            }
            DBUtils.Close(pst, gk);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
        return articleId;

    }
    @Override
    public void deleteArticle(long id) throws DaoException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, id);
            pst.executeUpdate();
            DBUtils.Close(pst);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
    }
//    @Override
//    public void deleteArticleByTitle(String title) throws DaoException {
//        try (Connection con = ConnectionFactory.getConnection();
//             PreparedStatement pst = con.prepareStatement(DELETE)) {
//            pst.setString(1, title);
//            pst.executeUpdate();
//            DBUtils.Close(pst);
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Error in db layer.");
//            throw new DaoException(e);
//        }
//    }

    @Override
    public List<Article> getAllArticles() throws DaoException {
        List<Article> list = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection()) {
            PreparedStatement pst = con.prepareStatement(SELECT_ALL);
            Article article;

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                article = new Article(rs.getLong("idArticle"),
                        rs.getString("title"),
                        rs.getString("time"),
                        rs.getInt("comment"),
                        rs.getString("content")
                );
                list.add(article);
            }
            DBUtils.Close(pst, rs);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public void updateArticle(Article article) throws DaoException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, article.getContent());
            pst.setString(2, article.getTitle());
            pst.executeUpdate();
            DBUtils.Close(pst);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
    }

    @Override
    public Article getArticleById(long id) throws DaoException {
        Article article = null;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT)) {
            pst.setLong(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                article = new Article(rs.getLong("idArticle"),
                        rs.getString("title"),
                        rs.getString("time"),
                        rs.getInt("comment"),
                        rs.getString("content")
                );
            }
            DBUtils.Close(pst, rs);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
        return article;
    }
}
