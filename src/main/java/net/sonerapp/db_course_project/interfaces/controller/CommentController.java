package net.sonerapp.db_course_project.interfaces.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.OkDto;
import net.sonerapp.db_course_project.application.dto.UnauthorizedDto;
import net.sonerapp.db_course_project.application.dto.CommentControllerDto.CommentDto;
import net.sonerapp.db_course_project.application.dto.CommentControllerDto.CreateCommentDto;
import net.sonerapp.db_course_project.application.dto.CommentControllerDto.DeleteCommentDto;
import net.sonerapp.db_course_project.core.model.Comment;
import net.sonerapp.db_course_project.core.service.CommentService;

@RestController
@RequestMapping("/api/v2/comment")
@Tag(name = "Comment")
public class CommentController {

    private final CommentService commentService;
    private final ConversionService conversionService;

    public CommentController(CommentService commentService, ConversionService conversionService) {
        this.commentService = commentService;
        this.conversionService = conversionService;
    }

    @Operation(summary = "Create Comment", description = "Creates a comment from the logged in user for the post of the id", responses = {
            @ApiResponse(responseCode = "200", description = "Comment created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDto.class))),
            @ApiResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    }, security = @SecurityRequirement(name = "accessAuth"))
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody @Valid CreateCommentDto createCommentDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        Comment comment = commentService.createComment(userDetails.getUsername(), createCommentDto.postUuid(),
                createCommentDto.comment());
        CommentDto commentDto = conversionService.convert(comment, CommentDto.class);

        return ResponseEntity.ok(commentDto);
    }

    @Operation(summary = "Delete Comment", description = "Deletes the comment if it is from the logged in user", responses = {
            @ApiResponse(responseCode = "200", description = "Comment deleted", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OkDto.class))),
            @ApiResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Invalid Comment Owner", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    }, security = @SecurityRequirement(name = "accessAuth"))
    @DeleteMapping
    public ResponseEntity<OkDto> deleteComment(@RequestBody @Valid DeleteCommentDto deleteCommentDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        commentService.deleteComment(deleteCommentDto.commentUuid(), userDetails);
        return ResponseEntity.ok(new OkDto("Successfully deleted"));
    }

}
