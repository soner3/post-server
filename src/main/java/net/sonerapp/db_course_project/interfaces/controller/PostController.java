package net.sonerapp.db_course_project.interfaces.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.OkDto;
import net.sonerapp.db_course_project.application.dto.PostController.CreatePostDto;
import net.sonerapp.db_course_project.application.dto.PostController.PostDto;
import net.sonerapp.db_course_project.application.dto.UserControllerDto.CreateUserDto;
import net.sonerapp.db_course_project.core.model.Post;
import net.sonerapp.db_course_project.core.service.PostService;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;
    private final ConversionService conversionService;
    
   
    
    public PostController(PostService postService, ConversionService conversionService) {
        this.postService = postService;
        this.conversionService = conversionService;
    }



    @PostMapping
        public ResponseEntity<PostDto> createPost (@RequestBody @Valid CreatePostDto createPostDto, @AuthenticationPrincipal UserDetails userDetails){
           Post post = postService.createPost(createPostDto.message(), userDetails);
           PostDto postDto = conversionService.convert(post, PostDto.class);

        return ResponseEntity.ok(postDto);
    }
    
    
    
    
}
