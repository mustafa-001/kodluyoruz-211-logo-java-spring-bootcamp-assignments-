package mutlu.movies.service;

import mutlu.movies.entity.Comment;
import mutlu.movies.entity.User;
import mutlu.movies.repository.CommentRepository;
import mutlu.movies.repository.MovieRepository;
import mutlu.movies.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Responsible for adding, deleting and querying by user, movie and updating
 * {@link Comment}'s
 */
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    public CommentService(CommentRepository commentRepository, MovieRepository movieRepository,
            UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    /**
     * Checks if User and Movie exists with given id numbers
     * 
     * @param request {@link mutlu.movies.entity.Comment} New comment with it's
     *                movie and user field's pointing to valid
     *                User and Movie entites.
     * @return If all checks are succesfull saves the request and returns it with commentId assigned.
     */
    public Comment create(Comment request) {
        log.info("Adding comment {} ", request);
        final var user = userRepository.findById(request.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User cannot be found"));
        final var movie = movieRepository.findById(request.getMovie().getMovieId())
                .orElseThrow(RuntimeException::new);

        if (!user.isPremium()) {
            log.info("Couldn't save comment because User: {} is not paid user", user.getUserId());
            throw new RuntimeException("Adding comments requires to be paid user.");
        }
        request.setUser(user);
        request.setMovie(movie);
        log.info("Added comment {} ", request);
        return commentRepository.save(request);
    }

    public Optional<Comment> getByCommentId(Long commentId) {
        log.debug("Getting comment by id {}", commentId);
        return commentRepository.findById(commentId);
    }

    /**
     * @return Comments associated with a {@link User} with a given username. If
     *         username doesn't exist empty List will be returned.
     */
    public List<Comment> getByUsername(String username) {
        log.debug("Getting comments by username {}", username);
        return commentRepository.findByUser_Username(username);
    }

    /**
     * @return Comment's associated with a given movieId. If movieId doesn't exist
     *         empty List will be returned.
     */
    public List<Comment> getByMovieId(Long movieId) {
        log.debug("Getting comments of movie {}", movieId);
        return commentRepository.findByMovie_MovieId(movieId);
    }

    public Comment update(Comment request, Long movieId) {
        return request;
    }

    /**
     * Deletes the comment wiht given id.
     * If this id doesn't exists it is ignored.
     * 
     */
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
