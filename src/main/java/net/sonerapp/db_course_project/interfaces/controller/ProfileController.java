package net.sonerapp.db_course_project.interfaces.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.UnauthorizedDto;
import net.sonerapp.db_course_project.application.dto.ProfileController.ProfileDto;
import net.sonerapp.db_course_project.application.dto.ProfileController.UpdateProfileDto;
import net.sonerapp.db_course_project.core.model.Profile;
import net.sonerapp.db_course_project.core.service.ProfileService;

@RestController
@RequestMapping("/api/v1/profile")
@Tag(name = "Profile")
public class ProfileController {

    private final ProfileService profileService;

    private final ConversionService conversionService;

    public ProfileController(ProfileService profileService, ConversionService conversionService) {
        this.profileService = profileService;
        this.conversionService = conversionService;
    }

    @Operation(summary = "Update Profile", description = "Updates the profile data of the logged in user", responses = {
            @ApiResponse(responseCode = "200", description = "Profile Updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileDto.class))),
            @ApiResponse(responseCode = "401", description = "Not Authenticated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
    })
    @PutMapping
    public ResponseEntity<ProfileDto> updateProfile(@AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid UpdateProfileDto updateProfileDto) {
        Profile profile = profileService.updateProfile(userDetails, updateProfileDto);
        ProfileDto profileDto = conversionService.convert(profile, ProfileDto.class);
        return ResponseEntity.ok(profileDto);
    }

}
