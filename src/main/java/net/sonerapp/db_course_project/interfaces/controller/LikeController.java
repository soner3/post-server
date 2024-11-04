package net.sonerapp.db_course_project.interfaces.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import net.sonerapp.db_course_project.application.dto.LikeControllerDto.CreateLikeDto;
import net.sonerapp.db_course_project.application.dto.LikeControllerDto.DeleteLikeDto;
import net.sonerapp.db_course_project.application.dto.LikeControllerDto.LikeDto;
import net.sonerapp.db_course_project.core.exceptions.LikeController.IllegalLikeException;
import net.sonerapp.db_course_project.core.model.Likes;
import net.sonerapp.db_course_project.core.service.LikeService;

@RestController
@RequestMapping("/api/v2/like")
@Tag(name = "Likes")
public class LikeController {

    private final LikeService likeService;
    private final ConversionService conversionService;

    public LikeController(LikeService likeService, ConversionService conversionService) {
        this.likeService = likeService;
        this.conversionService = conversionService;
    }

    @Operation(summary = "Create Like", description = "Creates a like from the logged in user for the post of the id", responses = {
            @ApiResponse(responseCode = "200", description = "Like Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LikeDto.class))),
            @ApiResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden Like", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    }, security = @SecurityRequirement(name = "accessAuth"))
    @PostMapping
    public ResponseEntity<LikeDto> createLike(@RequestBody @Valid CreateLikeDto createLikeDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        Likes like = likeService.createLike(userDetails.getUsername(), createLikeDto.postUuid());
        LikeDto likeDto = conversionService.convert(like, LikeDto.class);
        return ResponseEntity.ok(likeDto);
    }

    @Operation(summary = "Delete Like", description = "Deletes", responses = {
            @ApiResponse(responseCode = "200", description = "Like Deleted", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OkDto.class))),
            @ApiResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    }, security = @SecurityRequirement(name = "accessAuth"))
    @DeleteMapping
    public ResponseEntity<OkDto> deleteLike(@RequestBody @Valid DeleteLikeDto deleteLikeDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        likeService.deleteLike(deleteLikeDto.postUuid(), userDetails);
        return ResponseEntity.ok(new OkDto("Like deleted successfully"));
    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> illegalLike(IllegalLikeException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        problem.setTitle("Forbidden Like");
        problem.setDetail(e.getMessage());
        return ResponseEntity.of(problem).build();
    }

}
