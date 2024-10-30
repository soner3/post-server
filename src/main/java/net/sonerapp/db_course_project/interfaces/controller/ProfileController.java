package net.sonerapp.db_course_project.interfaces.controller;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sonerapp.db_course_project.application.dto.ProfileController.ProfileDto;
import net.sonerapp.db_course_project.application.dto.ProfileController.UpdateProfileDto;
import net.sonerapp.db_course_project.core.model.Profile;
import net.sonerapp.db_course_project.core.service.ProfileService;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    private final ConversionService conversionService;

    public ProfileController(ProfileService profileService, ConversionService conversionService) {
        this.profileService = profileService;
        this.conversionService = conversionService;
    }

    @PutMapping
    public ResponseEntity<ProfileDto> updateProfile(@AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UpdateProfileDto updateProfileDto) {
        Profile profile = profileService.updateProfile(userDetails, updateProfileDto);
        ProfileDto profileDto = conversionService.convert(profile, ProfileDto.class);
        return ResponseEntity.ok(profileDto);
    }

}
