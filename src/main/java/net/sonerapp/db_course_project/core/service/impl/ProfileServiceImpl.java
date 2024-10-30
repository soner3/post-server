package net.sonerapp.db_course_project.core.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.sonerapp.db_course_project.application.dto.ProfileController.UpdateProfileDto;
import net.sonerapp.db_course_project.core.model.Profile;
import net.sonerapp.db_course_project.core.model.User;
import net.sonerapp.db_course_project.core.repository.ProfileRepository;
import net.sonerapp.db_course_project.core.service.ProfileService;
import net.sonerapp.db_course_project.core.service.UserService;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserService userService;
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(UserService userService, ProfileRepository profileRepository) {
        this.userService = userService;
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile updateProfile(UserDetails userDetails, UpdateProfileDto updateProfileDto) {
        User user = userService.getUser(userDetails.getUsername());
        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new UsernameNotFoundException("No profile for the user found"));
        profile.setCity(updateProfileDto.city());
        profile.setCountry(updateProfileDto.country());
        profile.setGender(updateProfileDto.gender());
        profile.setStreet(updateProfileDto.street());
        profile.setStreetNumber(updateProfileDto.streetNumber());
        profile.setZipCode(updateProfileDto.zipCode());

        return profileRepository.save(profile);
    }

}
