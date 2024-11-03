package net.sonerapp.db_course_project.application.dto.LikeControllerDto;

import jakarta.validation.constraints.NotBlank;

public record DeleteLikeDto(@NotBlank(message = "The UUID Field can not be blank") String postUuid) {

}
