package entity;

import utils.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class Article implements Comparable {

    private long id;
    private String title;
    private String time;
    private int commentCount; // несколько
    private String content;

    public Article() {

    }

    public Article(String title, String content) {
        super();
        this.title = title;
        this.time = null;
        this.content = content;
    }

    public Article(Long id, String title, String time, int commentCount, String content) {
        super();
        this.id = id;
        this.title = title;
        this.time = time;
        this.commentCount = commentCount;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "entity.Article [id=" + id + ", title=" + title + ", time=" + time + ", comment=" + commentCount + ", content=" + content + "]";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Article article) {

            Date this_date = null;
            Date other_date = null;
            try {
                this_date = DateUtils.getDate(this.time);
                other_date = DateUtils.getDate(article.getTime());
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            assert this_date != null;
            return -this_date.compareTo(other_date);// TODO: 07.04.2023 check NPE
        }
        return 0;
    }
}
