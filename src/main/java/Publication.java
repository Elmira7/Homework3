import java.util.Date;

public class Publication {

    Long id;
    Long idAuthor;
    String title;
    String body;
    Date date;

    public Publication(Long id, Long idAuthor, String title, String body, Date date) {
        this.id = id;
        this.idAuthor = idAuthor;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public Publication(Long idAuthor, String title, String body, Date date) {
        this.idAuthor = idAuthor;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public Publication() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthorName(){
        UserStorage userStorage = new UserStorage();
        User user = userStorage.findUser(this.getIdAuthor());
        if (user != null){
            return user.getName();
        } else {
            return "unknown";
        }
    }
}

