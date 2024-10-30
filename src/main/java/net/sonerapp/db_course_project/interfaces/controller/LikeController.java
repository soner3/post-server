package net.sonerapp.db_course_project.interfaces.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.LikeControllerDto.CreateLikeDto;
import net.sonerapp.db_course_project.core.model.Like;
import net.sonerapp.db_course_project.core.service.LikeService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Like> createLike(@RequestBody @Valid CreateLikeDto createLikeDto, @AuthenticationPrincipal UserDetails userDetails) {
        Like like = likeService.createLike(userDetails.getUsername(), createLikeDto.comment(),1);
        return ResponseEntity.ok(like);
    }
    
}
