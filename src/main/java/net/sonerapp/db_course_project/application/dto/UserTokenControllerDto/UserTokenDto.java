package net.sonerapp.db_course_project.application.dto.UserTokenControllerDto;

import java.time.Instant;

import net.sonerapp.db_course_project.application.dto.UserControllerDto.UserDto;
import net.sonerapp.db_course_project.core.model.model_enums.UserTokenType;

public record UserTokenDto(
        long id, String token,
        Instant expiryDate,
        boolean isUsed,
        UserTokenType tokenType,
        UserDto userDto) {

}
