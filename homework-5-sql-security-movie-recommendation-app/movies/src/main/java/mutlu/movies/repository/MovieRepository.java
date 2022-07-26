package mutlu.movies.repository;

import mutlu.movies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByName(String name);

    List<Movie> findByUser_Username(String username);

    /**
     * @param userId
     * @return Number of movies added to system by given User with userId.
     */
    @Query("select count(m) from Movie m where user_user_id = :userId")
    Integer numberOfMoviesByUserId(Long userId);
}
