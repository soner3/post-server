package net.sonerapp.db_course_project.application.dto.LikeControllerDto;

import java.util.UUID;

import net.sonerapp.db_course_project.application.dto.ProfileController.ProfileDto;

public record LikeDto(UUID uuid, LikePostDto post, ProfileDto profile) {

}
