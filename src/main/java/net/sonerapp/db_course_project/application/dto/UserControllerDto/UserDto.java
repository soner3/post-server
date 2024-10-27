package net.sonerapp.db_course_project.application.dto.UserControllerDto;

import java.util.UUID;

public record UserDto(UUID uuid, String username, String email, String firstname, String lastname) {

}
