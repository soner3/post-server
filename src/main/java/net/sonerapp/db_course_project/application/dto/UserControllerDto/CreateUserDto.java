package net.sonerapp.db_course_project.application.dto.UserControllerDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
                @Size(min = 2, max = 100, message = "Username must be between 2 - 100 characters") String username,
                @Email(message = "Enter a valid email address") @Size(min = 1, max = 150, message = "The email should only have a character size from 1 - 150") String email,
                @Size(min = 8, max = 100, message = "Password must be between 8 - 100 characters") String password,
                @Size(min = 8, max = 100, message = "RePassword must be between 8 - 100 characters") String rePassword,
                @Size(min = 2, max = 100, message = "Firstname must be between 2 - 100 characters") String firstname,
                @Size(min = 2, max = 100, message = "Lastname must be between 2 - 100 characters") String lastname) {

}
