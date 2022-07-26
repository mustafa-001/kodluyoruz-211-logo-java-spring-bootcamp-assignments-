package mutlu.movies.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Entity representing a comment user makes.
 */
@Entity
//When another entity includes a Comment field when serializing/deserialing refer that field with it commentId.
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "commentId")
public class Comment {
    @Id
    @Column( nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long commentId;
    private String text;
    @ManyToOne
    @JoinColumn()
    @JsonIdentityReference(alwaysAsId = true) //When serializing refer with userId.
    private User user;
    @ManyToOne
    @JoinColumn()
    @JsonIdentityReference(alwaysAsId = true) //When serializing refer with movieId.
    private Movie movie;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //setter for user field to be used when deserializing from JSON.
    @JsonProperty("user")
    public void setUser(Long userId) {
        var user = new User();
        user.setUserId(userId);
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    //Setter for movie to be used when deserializing from JSON.
    @JsonProperty("movie")
    public void setMovie(Long movieId){
        var movie = new Movie();
        movie.setMovieId(movieId);
        this.movie = movie;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment [commentId=" + commentId + ", movie=" + movie + ", text=" + text + ", user=" + user + "]";
    }
}
