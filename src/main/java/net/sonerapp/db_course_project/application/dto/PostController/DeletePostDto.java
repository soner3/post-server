package net.sonerapp.db_course_project.application.dto.PostController;

import jakarta.validation.constraints.NotBlank;

public record DeletePostDto(@NotBlank String uuid) {

}
