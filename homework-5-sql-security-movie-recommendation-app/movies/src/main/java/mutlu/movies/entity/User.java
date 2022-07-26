package mutlu.movies.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing a user. Includes both account information (login etc) and usage details.
 */
@Entity(name = "users")
//When another entity includes a User field when serializng/deserialing refer that field with it userId.
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    private String username;

    private String email;
    private String passwordHash;
    private LocalDateTime premiumUntil;

    //Doesn't need setter with @JsonPreference annotation and a
    // List<Long> argument as Movie.comments field because we don't ever
    // need to deserialize a User with Comment and Movie already filled.
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Movie> movies;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Comment> comments;


    /**
     * @return True if this user stil a Paid User.
     */
    @JsonIgnore
    @Transient
    public boolean isPremium() {
        if (premiumUntil == null) {
            return false;
        }
        return this.premiumUntil.isAfter(LocalDateTime.now());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public LocalDateTime getPremiumUntil() {
        return premiumUntil;
    }

    public void setPremiumUntil(LocalDateTime premiumUntil) {
        this.premiumUntil = premiumUntil;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", premiumUntil=" + premiumUntil +
                ", movies=" + movies +
                '}';
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
