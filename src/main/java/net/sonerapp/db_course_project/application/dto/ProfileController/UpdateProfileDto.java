package net.sonerapp.db_course_project.application.dto.ProfileController;

import jakarta.validation.constraints.Size;
import net.sonerapp.db_course_project.core.model.model_enums.Gender;

public record UpdateProfileDto(
                Gender gender,
                @Size(min = 2, max = 255, message = "The country field must have a size between 2 and 255 characters") String country,
                @Size(min = 2, max = 255, message = "The street field must have a size between 2 and 255 characters") String street,
                @Size(min = 1, max = 5, message = "The street number field must atleast have 1 characters") int streetNumber,
                @Size(min = 5, max = 5, message = "The zip code field must and can only have 5 characters") int zipCode,
                @Size(min = 2, max = 255, message = "The street field must have a size between 2 and 255 characters") String city) {

}
