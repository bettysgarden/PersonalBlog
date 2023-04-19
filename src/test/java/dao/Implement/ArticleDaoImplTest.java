package dao.Implement;

import dao.Interface.ArticleDao;
import entity.Article;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleDaoImplTest {

    @Test
    @BeforeAll
    public static void testAddArticle() {
        ArticleDao dao = new ArticleDaoImpl();

//        Article article = new Article("Eras Tour",  "content ajslfkj lkjadlksj");
        try {
            List<Article> before = dao.getAllArticles();
            Article article = new Article("New life", "content ajslfkj lkjadlksj");
            Long addedArticle = dao.addArticle(article);
            System.out.println(addedArticle);
            List<Article> after = dao.getAllArticles();
            dao.deleteArticle(addedArticle);
            assertEquals(before.size() + 1, after.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllArticles() {

        ArticleDao dao = new ArticleDaoImpl();
        try {
            List<Article> allArticle = dao.getAllArticles();
            if (allArticle.size() > 0) {

                for (Article at : allArticle) {
                    System.out.println(at.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetArticleById() {

        ArticleDao dao = new ArticleDaoImpl();
        try {
            Article article = new Article("test get by id", "content ajslfkj lkjadlksj");
            Long addedArticle = dao.addArticle(article);
            Article getArticle = dao.getArticleById(addedArticle);
            dao.deleteArticle(addedArticle);
            assertEquals(article.getTitle(), getArticle.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @AfterAll
    public static void testDeleteArticle() {
        ArticleDao dao = new ArticleDaoImpl();
        try {
            Article article = new Article("New life", "content ajslfkj lkjadlksj");
            Long addedArticle = dao.addArticle(article);
            List<Article> before = dao.getAllArticles();
            dao.deleteArticle(addedArticle);
            List<Article> after = dao.getAllArticles();
            assertEquals(before.size() - 1, after.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}