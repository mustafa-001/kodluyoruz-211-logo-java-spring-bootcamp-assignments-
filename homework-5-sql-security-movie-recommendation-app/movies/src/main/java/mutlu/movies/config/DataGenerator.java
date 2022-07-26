package mutlu.movies.config;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import mutlu.movies.entity.Comment;
import mutlu.movies.entity.Movie;
import mutlu.movies.entity.User;
import mutlu.movies.repository.CommentRepository;
import mutlu.movies.repository.MovieRepository;
import mutlu.movies.repository.UserRepository;

@Component
//Only generate when "dev" profile is activated through application.properties.
@Profile("dev")
public class DataGenerator {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;
    private final Logger log = LoggerFactory.getLogger(DataGenerator.class);

    @Autowired
    public DataGenerator(UserRepository userRepository, MovieRepository movieRepository,
            CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event){
        if (userRepository.findAll().size() != 0 || movieRepository.findAll().size() != 0){
            log.debug("Database already contains data, skipping to add samples.");
            return;
        }
        log.info("Database is empty, filling with sample data.");
        User user1 = new User();
        user1.setEmail("user1@email.com");
        user1.setPasswordHash("sdadfjasljdflsakjdfkasjdfkasjd");
        user1.setPremiumUntil(LocalDateTime.now().plusMonths(6));
        user1.setUsername("username1");
        userRepository.save(user1);

        User user2 = new User();
        user2.setEmail("user2@email.com");
        user2.setPasswordHash("sdadfjasljdflsakjdfkasjdfkasjd");
        user2.setPremiumUntil(null);
        user2.setUsername("username2");
        userRepository.save(user2);

        User user3 = new User();
        user3.setEmail("user3@email.com");
        user3.setPasswordHash("sdadfjasljdflsakjdfkasjdfkasjd");
        user3.setPremiumUntil(LocalDateTime.now().minusDays(10));
        user3.setUsername("username3");
        userRepository.save(user3);

        Movie movie1 = new Movie();
        movie1.setName("The good the bas and the ugly");
        movie1.setUser(user1);
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setName("Lord of the rings");
        movie2.setUser(user3);
        movieRepository.save(movie2);

        Movie movie3 = new Movie();
        movie3.setName("Gone with the wind.");
        movie3.setUser(user1);
        movieRepository.save(movie3);

        Comment comment1 = new Comment();
        comment1.setMovie(movie1);
        comment1.setText("Güzel film.");
        comment1.setUser(user1);
        commentRepository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setMovie(movie1);
        comment2.setText("Çok uzun");
        comment2.setUser(user1);
        commentRepository.save(comment2);
        userRepository.flush();
    }
}
