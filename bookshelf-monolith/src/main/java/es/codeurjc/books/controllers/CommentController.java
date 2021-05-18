package es.codeurjc.books.controllers;

import es.codeurjc.books.dtos.requests.UpdateUserEmailRequestDto;
import es.codeurjc.books.dtos.requests.UserRequestDto;
import es.codeurjc.books.dtos.responses.UserCommentResponseDto;
import es.codeurjc.books.dtos.responses.UserResponseDto;
import es.codeurjc.books.services.CommentService;
import es.codeurjc.books.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/users")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Get all user's comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all user's comments",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserCommentResponseDto.class)))})})
    @GetMapping("/{userId}/comments")
    public Collection<UserCommentResponseDto> getUserComments(@Parameter(description = "id of user to get comments")
                                                              @PathVariable long userId) {
        return this.commentService.getComments(userId);
    }

}
