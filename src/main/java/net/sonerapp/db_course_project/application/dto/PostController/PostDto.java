package net.sonerapp.db_course_project.application.dto.PostController;

import net.sonerapp.db_course_project.application.dto.ProfileController.ProfileDto;

public record PostDto(String message, ProfileDto profile) {
}