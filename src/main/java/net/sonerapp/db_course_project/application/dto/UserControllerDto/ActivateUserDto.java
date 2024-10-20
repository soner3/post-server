package net.sonerapp.db_course_project.application.dto.UserControllerDto;

import jakarta.validation.constraints.Size;

public record ActivateUserDto(@Size(min = 1, max = 100, message = "Invalid token") String token) {

}
