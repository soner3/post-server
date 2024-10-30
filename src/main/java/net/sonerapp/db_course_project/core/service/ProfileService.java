package net.sonerapp.db_course_project.core.service;

import org.springframework.security.core.userdetails.UserDetails;

import net.sonerapp.db_course_project.application.dto.ProfileController.UpdateProfileDto;
import net.sonerapp.db_course_project.core.model.Profile;

public interface ProfileService {
    Profile updateProfile(UserDetails userDetails, UpdateProfileDto updateProfileDto);
}
