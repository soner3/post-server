package net.sonerapp.db_course_project.application.dto.CommentControllerDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCommentDto(
                @NotBlank(message = "The UUID Field can not be blank") String postUuid,
                @NotBlank(message = "The Comment Field can not be blank") @Size(min = 1, max = 160, message = "The Comment must be between 1 and 160 Characters") String comment) {
}
