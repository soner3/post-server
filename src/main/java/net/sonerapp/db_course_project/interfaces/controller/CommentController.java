package net.sonerapp.db_course_project.interfaces.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.CommentControllerDto.CommentDto;
import net.sonerapp.db_course_project.application.dto.CommentControllerDto.CreateCommentDto;
import net.sonerapp.db_course_project.core.model.Comment;
import net.sonerapp.db_course_project.core.service.CommentService;

@RestController
@RequestMapping("/api/v1/comment")
@Tag(name = "Comment")
public class CommentController {

    private final CommentService commentService;
    private final ConversionService conversionService;

    public CommentController(CommentService commentService, ConversionService conversionService) {
        this.commentService = commentService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody @Valid CreateCommentDto createCommentDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        Comment comment = commentService.createComment(userDetails.getUsername(), createCommentDto.uuid(),
                createCommentDto.comment());
        CommentDto commentDto = conversionService.convert(comment, CommentDto.class);

        return ResponseEntity.ok(commentDto);
    }
}
