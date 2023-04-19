package utils;

import entity.Article;

import java.util.List;

/**
 * entity.Article processing tool
 *
 */
public class ArticleUtils {

    /**
     * Process time
     *
     * @param list
     * @return
     */
    public static List cutTime(List<Article> list) {

        for (Article a : list) {
            a.setTime(a.getTime().substring(0, 11));
        }

        return list;
    }

    public static Article cutTime(Article a) {
        a.setTime(a.getTime().substring(0, 11));
        return a;
    }

    /**
     * Process the content of the next article
     *
     * @param list
     * @return
     */
    public static List cutContent(List<Article> list) {

        for (Article a : list) {
            if (a.getContent() != null && a.getContent().length() > 351) {
                a.setContent(a.getContent().substring(0, 349) + "...");
            }
        }
        return list;
    }
}