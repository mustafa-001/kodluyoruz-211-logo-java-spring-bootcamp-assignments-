package mutlu.movies.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import mutlu.movies.entity.Comment;
import mutlu.movies.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    static private final String commentRequestBodyExample = """
            {
              "text": "Harika bir film",
              "user": 1,
              "movie": 10
            }""";

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Get all comments by given movie Id.")
    @GetMapping("/{movieId}")
    public List<Comment> getByMovieId(@PathVariable @Parameter(example = "10") Long movieId) {
        return commentService.getByMovieId(movieId);
    }

    @Operation(summary = "Add a new comment with give user id and movie Id. Requires this user to be paid user.")
    @PostMapping
    public Comment add(@RequestBody
                       @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value = commentRequestBodyExample),
                               mediaType = MediaType.APPLICATION_JSON_VALUE))
                       Comment request) {
        return commentService.create(request);
    }

    @Operation(summary = "Delete the comment with given comment Id.")
    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable @Parameter(example = "12") Long commentId) {
        commentService.delete(commentId);
    }
}
