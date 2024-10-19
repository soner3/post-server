package net.sonerapp.db_course_project.application.service.user;

import org.springframework.http.ResponseEntity;

import net.sonerapp.db_course_project.application.dto.UserControllerDto.CreateUserDto;

public interface UserControllerService {
    ResponseEntity<?> createUser(CreateUserDto userData);
}
