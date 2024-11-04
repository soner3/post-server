package net.sonerapp.db_course_project.application.dto.CommentControllerDto;

import jakarta.validation.constraints.NotBlank;

public record DeleteCommentDto(
                @NotBlank(message = "The UUID Field can not be blank") String commentUuid) {

}
