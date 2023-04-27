package service.Implement;

import dao.Implement.CommentDaoImpl;
import dao.Interface.CommentDao;
import entity.Comment;
import exception.BusinessException;
import exception.DaoException;
import service.Interface.CommentServiceInterface;

import java.util.List;
import java.util.logging.Logger;

public class CommentService implements CommentServiceInterface {
    private static final Logger logger = Logger.getLogger(CommentService.class.getName());

    private final CommentDao commentDao;


    public CommentService() {
        commentDao = new CommentDaoImpl();
    }

    @Override
    public List<Comment> loadComments(long article_id) throws BusinessException {
        try {
            return commentDao.getCommentsForArticle(article_id);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }    @Override
    public Comment getCommentById(long id) throws BusinessException {
        try {
            return commentDao.getCommentById(id);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public long addComment(Comment comment) throws BusinessException {
        try {
            return commentDao.addComment(comment);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }

    @Override
    public void deleteComment(Long id) throws BusinessException {
        try {
            commentDao.deleteComment(id);
        } catch (DaoException ex) {
            throw new BusinessException(ex);
        }
    }

}
