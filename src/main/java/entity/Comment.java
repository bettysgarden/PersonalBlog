package entity;

public class Comment {

    private long id;
    private long idArticle;
    private long idUser;
    private String content;
    private String time;
    private String nickname;

    public Comment() {
        super();
    }

    public Comment(Long id, long idArticle, long idUser, String content, String time) {
        this.id = id;
        this.idArticle = idArticle;
        this.idUser = idUser;
        this.content = content;
        this.time = time;
    }

    public Comment(long idArticle, long idUser, String content) {
        this.idArticle = idArticle;
        this.idUser = idUser;
        this.content = content;
    }


    @Override
    public String toString() {
        return "Comment [id=" + id + ", parent_article_id=" + idArticle + ", content=" + content + ", time=" + time + ", user_id=" + idUser + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
}
