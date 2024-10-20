package net.sonerapp.db_course_project.application.dto.AuthControllerDto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(@NotBlank String username, @NotBlank String password) {

}
