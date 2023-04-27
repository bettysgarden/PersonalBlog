package service.Interface;

import entity.Comment;
import exception.BusinessException;

import java.util.List;

public interface CommentServiceInterface {
    List<Comment> loadComments(long article_id) throws BusinessException;

    long addComment(Comment comment) throws BusinessException;

    void deleteComment(Long id) throws BusinessException;
    Comment getCommentById(long id) throws BusinessException;

    }
