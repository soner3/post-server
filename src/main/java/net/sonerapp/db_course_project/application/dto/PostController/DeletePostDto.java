package net.sonerapp.db_course_project.application.dto.PostController;

import jakarta.validation.constraints.NotBlank;

public record DeletePostDto(@NotBlank(message = "The UUID Field can not be blank") String uuid) {

}
