package mutlu.movies.service;

import mutlu.movies.dto.EmailDto;
import mutlu.movies.entity.Movie;
import mutlu.movies.repository.MovieRepository;
import mutlu.movies.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final AmqpTemplate rabbitTemplate;
    private final Logger log = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    public MovieService(MovieRepository movieRepository, UserRepository userRepository, AmqpTemplate rabbitTemplate) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

    /**
     * Checks if given userId corresponds to existing user, if not throws an
     * exception.
     * Checks if user is capable of adding more than 3 movies.
     * 
     * @return If all checks are succesfull saves the request and returns it with
     *         movieId assigned.
     */
    public Movie create(Movie request) {
        log.info("Adding movie {}", request);
        var user = userRepository.findById(request.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User with given Id cannot be found."));
        Integer movieCount = movieRepository.numberOfMoviesByUserId(user.getUserId());
        log.debug("Movies by this user: {} ", movieCount);
        if (movieCount <= 3 || user.isPremium()) {
            try {
                //Send an email request to email service for each user.
                userRepository.findAll().forEach(registeredUser -> {
                    log.info("Sending message to email service {}", registeredUser.getEmail());
                    rabbitTemplate.convertAndSend("movies.email", new EmailDto(registeredUser.getEmail(),
                            registeredUser.getUsername(), request.getName(), "Film hakkında açıklama."));
                });
            } catch (Exception c) {
                log.info("RabbitMQ connection refused. Continuing.");
            }
            return movieRepository.save(request);

        } else {
            log.info("User is both have already 3 movie and not premium {}", user);
            throw new RuntimeException("Adding more than 3 movies requires to be paid user.");
        }
    }

    /**
     * @return List of {@link Movie} by given username.
     */
    public Collection<Movie> getByUsername(String username) {
        return movieRepository.findByUser_Username(username);
    }

    public Movie getById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie with given Id cannot be found."));
    }

    public Movie update(Movie request, Long movieId) {
        return request;
    }

    /**
     * Deletes the {@link Movie} with given movieId.
     * If entity doesn't exist an exception is thrown.
     * 
     */
    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }

}
