package net.sonerapp.db_course_project.application.dto.PostController;

import java.util.UUID;

import net.sonerapp.db_course_project.application.dto.ProfileController.ProfileDto;

public record PostDto(UUID uuid, String message, ProfileDto profile) {
}