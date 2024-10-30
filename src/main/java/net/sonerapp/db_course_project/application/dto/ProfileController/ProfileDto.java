package net.sonerapp.db_course_project.application.dto.ProfileController;

import java.util.UUID;

import net.sonerapp.db_course_project.application.dto.UserControllerDto.UserDto;
import net.sonerapp.db_course_project.core.model.model_enums.Gender;

public record ProfileDto(
        UUID uuid,
        Gender gender,
        String country,
        String street,
        int streetNumber,
        int zipCode,
        String city,
        UserDto user) {

}
