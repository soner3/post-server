package net.sonerapp.db_course_project.application.dto.UserControllerDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record EmailDto(
        @Email(message = "Enter a valid email address") @Size(min = 1, max = 150, message = "The email should only have a character size from 1 - 150") String email) {

}
