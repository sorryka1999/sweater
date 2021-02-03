package letsCode.sweater.domain;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;
    private String tag;
    private String filename;

    // mapping with hibernate annotation (many messages to one user)
    @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn annotation is NOT NECESSARY !!!
    // setting column name "user_id" instead of "author"
    @JoinColumn(name = "user_id")
    private User author;

    public String getAuthorName() {
        // answer by author of this course to the question by follower in comments:
        //   Как связаны {{authorName}} в main.html и getAuthorName(),
        //   которую мы добавили в Message.java?
        //   Это закидоны из groovy.
        //   Всё методы getSomething могут быть заменены на обращение к полю
        //   (даже если оно не существует) с именем something
        return author != null ? author.getUsername() : "<none>";
    }

    public Message() {
    }

    public Message(String text, String tag, User author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
