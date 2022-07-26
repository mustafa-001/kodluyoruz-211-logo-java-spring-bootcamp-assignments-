package mutlu.movies.entity;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Entity representing a movie users added to ty system.
 */
@Entity
//When another entity includes a Movie field when serializng/deserialing refer that field with it movieId
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "movieId")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    private String name;

    @ManyToOne
    @JoinColumn()
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private List<Comment> commentList;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    //set for commentList field to be used when deserializing from JSON.
    @JsonProperty("commentList")
    public void setCommentListByIds(List<Long> commentIdList) {
        this.commentList.addAll(commentIdList.stream().map(id -> {
            var c = new Comment();
            c.setCommentId(id);
            return c;
        }).toList());
    }
}
