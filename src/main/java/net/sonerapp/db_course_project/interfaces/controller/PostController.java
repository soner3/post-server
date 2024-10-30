package net.sonerapp.db_course_project.interfaces.controller;

<<<<<<< HEAD
=======
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
>>>>>>> c20f5f772df06be7ee115a4e2da24ca54904596c
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.OkDto;
import net.sonerapp.db_course_project.application.dto.PostController.CreatePostDto;
import net.sonerapp.db_course_project.core.service.PostService;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;
<<<<<<< HEAD

    public PostController(PostService postService) {
=======
    private final ConversionService conversionService;
    
   
    
    public PostController(PostService postService, ConversionService conversionService) {
>>>>>>> c20f5f772df06be7ee115a4e2da24ca54904596c
        this.postService = postService;
        this.conversionService = conversionService;
    }

<<<<<<< HEAD
    @PostMapping("/public/create")
    public ResponseEntity<OkDto> createUser(@RequestBody @Valid CreatePostDto createPostDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        postService.createPost(createPostDto.message(), userDetails);
        return ResponseEntity.ok(new OkDto("User created successfully. An activation mail has been sent"));
=======


    @PostMapping
        public ResponseEntity<PostDto> createPost (@RequestBody @Valid CreatePostDto createPostDto, @AuthenticationPrincipal UserDetails userDetails){
           Post post = postService.createPost(createPostDto.message(), userDetails);
           PostDto postDto = conversionService.convert(post, PostDto.class);

        return ResponseEntity.ok(postDto);
>>>>>>> c20f5f772df06be7ee115a4e2da24ca54904596c
    }

}
