package net.sonerapp.db_course_project.core.service.impl;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sonerapp.db_course_project.application.dto.ProfileController.UpdateProfileDto;
import net.sonerapp.db_course_project.core.exceptions.EntityNotFoundException;
import net.sonerapp.db_course_project.core.exceptions.NoEntityDeletedException;
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

    @Override
    @Transactional
    public void deleteProfile(UserDetails userDetails) {
        User user = userService.getUser(userDetails.getUsername());
        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("No profile found for the user"));

        int deletedEntityCount = profileRepository.deleteByUuid(profile.getUuid());

        if (deletedEntityCount > 0) {
            return;
        } else {
            throw new NoEntityDeletedException("No entity found to delete");
        }
    }

    @Override
    public Stream<Profile> getAllProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable).stream();
    }

}
