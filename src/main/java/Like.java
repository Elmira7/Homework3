import java.util.Date;

public class Like {
    Long idAuthor;
    Long idPost;
    Date date;
    String nameAuthor;
    String titlePost;

    public Like(Long idAuthor, Long idPost, Date date) {
        this.idAuthor = idAuthor;
        this.idPost = idPost;
        this.date = date;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public Like(Long idAuthor, Long idPost, Date date, String nameAuthor, String titlePost) {
        this.idAuthor = idAuthor;
        this.idPost = idPost;
        this.date = date;
        this.nameAuthor = nameAuthor;
        this.titlePost = titlePost;
    }

    public Like(Long idAuthor, Long idPost) {
        this.idAuthor = idAuthor;
        this.idPost = idPost;
    }

    public Like() {

    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
