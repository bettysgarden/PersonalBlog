package dao.Implement;

import dao.Interface.CommentDao;
import entity.Comment;
import exception.DaoException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentDaoTest {
    @Test
    public void testAddComment() throws DaoException {
        try {
        Comment cm = new Comment(68, 1, "FUCK OFF");
        CommentDao dao = new CommentDaoImpl();
        List<Comment> before = dao.getCommentsForArticle(68L);
        Long addedComment = dao.addComment(cm);
        System.out.println(addedComment);
        List<Comment> after = dao.getCommentsForArticle(68L);
        assertEquals(before.size() + 1, after.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetComment() throws DaoException {

        CommentDao dao = new CommentDaoImpl();

        List cms = dao.getCommentsForArticle(68L);

        Iterator it = cms.iterator();

        Comment cm;
        while (it.hasNext()) {
            cm = (Comment) it.next();
            System.out.println(cm.toString());
        }
    }
    @Test
    public void testDeleteComment() {
        CommentDao dao = new CommentDaoImpl();
        try {
            Comment cm = new Comment(68L, 1, "FUCK OFF");
            Long addedComment = dao.addComment(cm);
            List<Comment> before = dao.getCommentsForArticle(68L);
            dao.deleteComment(addedComment);
            List<Comment> after = dao.getCommentsForArticle(68L);
            assertEquals(before.size() - 1, after.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
