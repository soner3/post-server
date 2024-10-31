package net.sonerapp.db_course_project.application.dto.CommentControllerDto;

import net.sonerapp.db_course_project.application.dto.ProfileController.ProfileDto;

public record CommentDto(String uuid, String content, CommentPostDto post, ProfileDto profile) {

}
