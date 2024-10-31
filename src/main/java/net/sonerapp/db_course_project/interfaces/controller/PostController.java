package net.sonerapp.db_course_project.interfaces.controller;

import java.util.stream.Stream;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.PostController.CreatePostDto;
import net.sonerapp.db_course_project.application.dto.PostController.PostDto;
import net.sonerapp.db_course_project.core.model.Post;
import net.sonerapp.db_course_project.core.service.PostService;

@RestController
@RequestMapping("/api/v1/post")
@Tag(name = "Post")
public class PostController {

    private final PostService postService;

    private final ConversionService conversionService;

    public PostController(PostService postService, ConversionService conversionService) {
        this.postService = postService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid CreatePostDto createPostDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        Post post = postService.createPost(createPostDto.message(), userDetails);
        PostDto postDto = conversionService.convert(post, PostDto.class);

        return ResponseEntity.ok(postDto);
    }

    @GetMapping
    public ResponseEntity<Stream<PostDto>> getAllPosts(Pageable pageable) {
        Stream<PostDto> postList = postService.getPostList(pageable)
                .map(post -> conversionService.convert(post, PostDto.class));
        return ResponseEntity.ok(postList);
    }

}
