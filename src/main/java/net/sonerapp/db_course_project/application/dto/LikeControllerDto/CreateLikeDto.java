package net.sonerapp.db_course_project.application.dto.LikeControllerDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateLikeDto( 
    @Size(min = 2, max = 160, message = "Message must be between 2 - 160 characters")@NotBlank(message = "The Message Field can not be blank") String comment) 
    {
   
}
