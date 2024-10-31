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
import net.sonerapp.db_course_project.application.dto.LikeControllerDto.CreateLikeDto;
import net.sonerapp.db_course_project.application.dto.LikeControllerDto.LikeDto;
import net.sonerapp.db_course_project.core.model.Likes;
import net.sonerapp.db_course_project.core.service.LikeService;

@RestController
@RequestMapping("/api/v1/like")
@Tag(name = "Likes")
public class LikeController {

    private final LikeService likeService;
    private final ConversionService conversionService;

    public LikeController(LikeService likeService, ConversionService conversionService) {
        this.likeService = likeService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<LikeDto> createLike(@RequestBody @Valid CreateLikeDto createLikeDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        Likes like = likeService.createLike(userDetails.getUsername(), createLikeDto.uuid());
        LikeDto likeDto = conversionService.convert(like, LikeDto.class);
        return ResponseEntity.ok(likeDto);
    }

}
