package net.sonerapp.db_course_project.application.dto.PostController;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostDto(
        @Size(min = 2, max = 100, message = "Message must be between 2 - 100 characters") @NotBlank(message = "The Message Field can not be blank") String message) {

}