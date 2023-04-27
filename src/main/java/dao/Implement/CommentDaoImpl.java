package dao.Implement;

import dao.Interface.CommentDao;
import utils.ConnectionFactory;
import entity.Comment;
import exception.DaoException;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDaoImpl implements CommentDao {
    private static final Logger logger = Logger.getLogger(CommentDaoImpl.class.getName());
    private final ConnectionFactory builder = new ConnectionFactory();
    protected Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    private static final String SELECT
            = "SELECT idcomment, content, time, article_idarticle, user_iduser, nickname  " +
            "FROM comment WHERE article_idarticle=? ORDER BY TIME";

    private static final String SELECT_ONE
            = "SELECT idcomment, content, time, article_idarticle, user_iduser, nickname FROM comment WHERE idcomment = ?";

    private static final String UPDATE_SUB
            = "UPDATE article SET COMMENT = COMMENT - 1 WHERE idarticle=?";

    private static final String UPDATE_INC
            = "UPDATE article SET COMMENT = COMMENT + 1 WHERE idarticle=?";

    private static final String INSERT
            = "INSERT INTO comment (content, article_idarticle, user_iduser, nickname) VALUES (?, ?, ?,?)";// +
            // "(SELECT name FROM user_to_comment WHERE iduser = ?))";

    private static final String DELETE
            = "DELETE FROM comment WHERE idcomment = ?";


    @Override
    public void deleteComment(Long commentId) throws DaoException {
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(DELETE);
            pst.setLong(1, commentId);
            articleSubComment(con, commentId);
            pst.executeUpdate();

            DBUtils.Close(pst);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
    }

    private void articleSubComment(Connection conn, Long commentId) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(SELECT_ONE);
            //сначала выясняем id статьи, к которой добавили комментарий
            pst.setLong(1, commentId);
            ResultSet rs = pst.executeQuery();
            long article_id = 0;
            if (rs.next()) {
                article_id = rs.getInt("article_idarticle");
            }

            PreparedStatement pst1 = conn.prepareStatement(UPDATE_SUB);
                pst1.setLong(1, article_id);
                pst1.executeUpdate();
    }

    @Override
    public Long addComment(Comment comment) throws DaoException {
        long commentId = -1L;

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"idcomment"})) {

            pst.setString(1, comment.getContent());
            pst.setLong(2, comment.getIdArticle());
            pst.setLong(3, comment.getIdUser());
            pst.setString(4, comment.getNickname());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                commentId = gk.getLong("idcomment");
            }
            DBUtils.Close(pst, gk);

            PreparedStatement pst2 = con.prepareStatement(UPDATE_INC);
            pst2.setLong(1, comment.getIdArticle());
            pst2.executeUpdate();
            DBUtils.Close(pst2);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
        return commentId;

    }

    @Override
    public List<Comment> getCommentsForArticle(Long articleId) throws DaoException {
        List<Comment> list;

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT)) {

            pst.setLong(1, articleId);
            ResultSet rs = pst.executeQuery();
            Comment cm;
            list = new ArrayList<>();
            while (rs.next()) {
                cm = new Comment();
                cm.setId(rs.getLong("idcomment"));
                cm.setIdArticle(rs.getLong("article_idarticle"));
                cm.setTime(rs.getString("time"));
                cm.setContent(rs.getString("content"));
                cm.setIdUser(rs.getLong("user_iduser"));
                cm.setNickname(rs.getString("nickname"));
                list.add(cm);
            }
            DBUtils.Close(pst, rs);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
        return list;
    }
    @Override
    public Comment getCommentById(Long id) throws DaoException {

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ONE)) {

            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            Comment cm = null;
            if (rs.next()) {
                cm = new Comment();
                cm.setId(rs.getLong("idcomment"));
                cm.setIdArticle(rs.getLong("article_idarticle"));
                cm.setTime(rs.getString("time"));
                cm.setContent(rs.getString("content"));
                cm.setIdUser(rs.getLong("user_iduser"));
                cm.setNickname(rs.getString("nickname"));
            }
            DBUtils.Close(pst, rs);
            return cm;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in db layer.");
            throw new DaoException(e);
        }
    }
}
