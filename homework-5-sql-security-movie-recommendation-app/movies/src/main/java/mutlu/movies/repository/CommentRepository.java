package mutlu.movies.repository;

import mutlu.movies.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMovie_MovieId(Long movieId);

    List<Comment> findByUser_Username(String username);
}
