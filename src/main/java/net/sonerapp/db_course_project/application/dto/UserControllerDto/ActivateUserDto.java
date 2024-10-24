package net.sonerapp.db_course_project.application.dto.UserControllerDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActivateUserDto(
        @Size(min = 1, max = 150, message = "Invalid token") @NotBlank(message = "The Token Field can not be blank") String token) {

}
