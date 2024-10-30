package net.sonerapp.db_course_project.interfaces.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.OkDto;
import net.sonerapp.db_course_project.application.dto.PostController.CreatePostDto;
import net.sonerapp.db_course_project.application.dto.UserControllerDto.CreateUserDto;
import net.sonerapp.db_course_project.core.service.PostService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;
    
    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    @PostMapping("/public/create")
        public ResponseEntity<OkDto>createUser (@RequestBody @Valid CreatePostDto createPostDto, @AuthenticationPrincipal UserDetails userDetails){
            postService.createPost(createPostDto.message(), userDetails);
        return ResponseEntity.ok (new OkDto("User created successfully. An activation mail has been sent"));
    }
    
    
    
    
}
