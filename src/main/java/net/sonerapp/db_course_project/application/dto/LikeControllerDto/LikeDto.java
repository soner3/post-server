package net.sonerapp.db_course_project.application.dto.LikeControllerDto;

import net.sonerapp.db_course_project.application.dto.ProfileController.ProfileDto;

public record LikeDto(String uuid, LikePostDto post, ProfileDto profile) {

}
