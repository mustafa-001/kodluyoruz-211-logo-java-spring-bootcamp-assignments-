package mutlu.movies.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import mutlu.movies.entity.Movie;
import mutlu.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    static private final String movieRequestBodyExample = """
            {
                "name": "Indiana jones",
                "user": 1
            }""";

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get all movies.")
    @GetMapping
    public List<Movie> getAll(){
        return movieService.getAll();
    }

    @Operation(summary = "Get movies added by User with given username.")
    @GetMapping("/user/{username}")
    public Collection<Movie> getByUserId(@PathVariable @Parameter(example = "username1") String username) {
        return movieService.getByUsername(username);
    }

    @Operation(summary = "Get the movie by movie Id.")
    @GetMapping("/{movieId}")
    public Movie getByMovieId(@PathVariable @Parameter(example = "4") Long movieId) {
        return movieService.getById(movieId);
    }

    @Operation(summary = "Add a new movie. Requires user to be paid user to add more than 3 movies.")
    @PostMapping
    public Movie add(@RequestBody
                     @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value = movieRequestBodyExample),
                             mediaType = MediaType.APPLICATION_JSON_VALUE))
                     Movie request) {
        return movieService.create(request);
    }

    @Operation(summary = "Update details of a movie with given movie Id.")
    @PutMapping("/{movieId}")
    public Movie update(@RequestBody
                        @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value = movieRequestBodyExample),
                                mediaType = MediaType.APPLICATION_JSON_VALUE))
                        Movie request, @PathVariable @Parameter(example = "4") Long movieId) {
        return movieService.update(request, movieId);
    }

    @Operation(summary = "Delete the movie with given movie Id.")
    @DeleteMapping("/{movieId}")
    public void delete(@PathVariable @Parameter(example = "4") Long movieId) {
        movieService.delete(movieId);
    }
}
