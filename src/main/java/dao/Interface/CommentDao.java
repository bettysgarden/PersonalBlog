package dao.Interface;

import entity.Comment;
import exception.DaoException;

import java.util.List;

public interface CommentDao {

    void deleteComment(Long comment_id) throws DaoException;

    Long addComment(Comment comment) throws DaoException;

    List<Comment> getCommentsForArticle(Long article_id) throws DaoException;
    Comment getCommentById(Long id) throws DaoException;
}
