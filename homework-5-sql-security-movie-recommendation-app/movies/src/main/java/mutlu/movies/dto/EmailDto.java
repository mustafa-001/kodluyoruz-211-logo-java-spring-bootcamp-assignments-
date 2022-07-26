package mutlu.movies.dto;


import mutlu.movies.entity.Movie;

/**
 * Dto to used when sending a email request to message queue.
 * {@link mutlu.movies.service.MovieService#create(Movie)}
 */
public class EmailDto {
    private String email;
    private String name;
    private String title;
    private String text;

    public EmailDto(String email, String name, String title, String text) {
        this.email = email;
        this.name = name;
        this.title = title;
        this.text = text;
    }

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

