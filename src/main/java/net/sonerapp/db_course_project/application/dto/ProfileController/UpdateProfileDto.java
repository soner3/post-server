package net.sonerapp.db_course_project.application.dto.ProfileController;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import net.sonerapp.db_course_project.core.model.model_enums.Gender;

public record UpdateProfileDto(
                Gender gender,
                @Size(min = 2, max = 255, message = "The country field must have a size between 2 and 255 characters") String country,
                @Size(min = 2, max = 255, message = "The street field must have a size between 2 and 255 characters") String street,
                @Min(value = 1, message = "Please enter a valid street number") @Max(value = 1000, message = "Please enter a valid street number") int streetNumber,
                @Min(value = 10000, message = "The Zip Code value must have minimum a value of 10000") @Max(value = 100000, message = "The Zip Code value must have max a value of 100000") int zipCode,
                @Size(min = 2, max = 255, message = "The street field must have a size between 2 and 255 characters") String city) {

}
