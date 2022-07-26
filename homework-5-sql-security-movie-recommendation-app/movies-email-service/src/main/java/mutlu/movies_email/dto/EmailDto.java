package mutlu.movies_email.dto;

/**
 * Dto to used when sending a email request to message queue.
 */

public class EmailDto {
    private String email;
    private String name;
    private String title;
    private String text;

    public EmailDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EmailDto(String email, String title, String text) {
        super();
        this.email = email;
        this.title = title;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmailDto [email=" + email + ", name=" + name + ", text=" + text + ", title=" + title + "]";
    }
}
