package net.sonerapp.db_course_project.application.dto.PostController;

import net.sonerapp.db_course_project.application.dto.ProfileController.ProfileDto;

public record PostCommentDto(String uuid, String content, ProfileDto profile) {

}
