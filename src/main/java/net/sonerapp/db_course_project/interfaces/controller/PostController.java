package net.sonerapp.db_course_project.interfaces.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
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
import net.sonerapp.db_course_project.application.dto.LikeControllerDto.LikeDto;
import net.sonerapp.db_course_project.application.dto.PostController.CreatePostDto;
import net.sonerapp.db_course_project.application.dto.PostController.DeletePostDto;
import net.sonerapp.db_course_project.application.dto.PostController.PostCommentDto;
import net.sonerapp.db_course_project.application.dto.PostController.PostDto;
import net.sonerapp.db_course_project.application.dto.PostController.PostLikeDto;
import net.sonerapp.db_course_project.application.dto.PostController.PostListItemDto;
import net.sonerapp.db_course_project.core.exceptions.PostController.InvalidPostOwnerException;
import net.sonerapp.db_course_project.core.model.Post;
import net.sonerapp.db_course_project.core.service.PostService;
import net.sonerapp.db_course_project.interfaces.exceptions.ConvertetToNullException;

@RestController
@RequestMapping("/api/v2/post")
@Tag(name = "Post")
public class PostController {

    private final PostService postService;

    private final ConversionService conversionService;

    public PostController(PostService postService, ConversionService conversionService) {
        this.postService = postService;
        this.conversionService = conversionService;
    }

    @Operation(summary = "Create Post", description = "Creates a post for the logged in user", responses = {
            @ApiResponse(responseCode = "200", description = "Post created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))),
            @ApiResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    }, security = @SecurityRequirement(name = "accessAuth"))
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid CreatePostDto createPostDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        Post post = postService.createPost(createPostDto.message(), userDetails);
        PostDto postDto = conversionService.convert(post, PostDto.class);

        return ResponseEntity.ok(postDto);
    }

    @Operation(summary = "All Posts", description = "Returns all posts that exists in the db in form of pages", responses = {
            @ApiResponse(responseCode = "200", description = "Request successfull", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto[].class))),
            @ApiResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    }, security = @SecurityRequirement(name = "accessAuth"))
    @GetMapping
    public ResponseEntity<List<PostListItemDto>> getAllPosts(Pageable pageable) {
        List<PostListItemDto> postList = new ArrayList<>();
        postService.getPostList(pageable)
                .forEach(post -> {
                    List<PostCommentDto> commentList = post.getComments().stream()
                            .map(comment -> {
                                CommentDto commentDto = conversionService.convert(comment, CommentDto.class);
                                if (commentDto != null) {
                                    return new PostCommentDto(commentDto.uuid(), commentDto.content(),
                                            commentDto.profile());
                                } else {
                                    throw new ConvertetToNullException(
                                            "The Comment entity could not be successfully convertet to DTO");
                                }
                            }).toList();

                    List<PostLikeDto> likeList = post.getLikes().stream().map(like -> {
                        LikeDto likeDto = conversionService.convert(like, LikeDto.class);
                        if (likeDto != null) {
                            return new PostLikeDto(likeDto.uuid(), likeDto.profile());
                        } else {
                            throw new ConvertetToNullException(
                                    "The Comment entity could not be successfully convertet to DTO");
                        }
                    }).toList();
                    int likeCount = likeList.size();
                    PostDto postDto = conversionService.convert(post, PostDto.class);
                    postList.add(new PostListItemDto(postDto, commentList, likeCount, likeList));
                });
        return ResponseEntity.ok(postList);
    }

    @Operation(summary = "Delete Post", description = "Deletes the post if it is from the logged in user", responses = {
            @ApiResponse(responseCode = "200", description = "Post deleted", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OkDto.class))),
            @ApiResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Invalid Post Owner", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    }, security = @SecurityRequirement(name = "accessAuth"))
    @DeleteMapping
    public ResponseEntity<OkDto> deletePost(@RequestBody @Valid DeletePostDto deletePostDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        postService.deletePost(deletePostDto.uuid(), userDetails);
        return ResponseEntity.ok(new OkDto("Post deleted successfully"));
    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> invalidPostOwner(InvalidPostOwnerException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        problem.setTitle("Invalid Post Owner");
        problem.setDetail(e.getMessage());
        return ResponseEntity.of(problem).build();
    }

}
