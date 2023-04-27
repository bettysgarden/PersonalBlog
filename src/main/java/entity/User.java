package entity;

public class User {

    private long id;
    private String userName;
    private String email;
    public User() {
        super();
    }
    public User(long id, String userName, String email) {
        super();
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
    public User(String userName, String email) {
        super();
        this.userName = userName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User [userid=" + id + ", username=" + userName
                 + ", email=" + email + "]";
    }
}