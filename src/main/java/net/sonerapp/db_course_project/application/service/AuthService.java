package net.sonerapp.db_course_project.application.service;

import org.springframework.http.ResponseEntity;

import net.sonerapp.db_course_project.application.dto.OkDto;
import net.sonerapp.db_course_project.application.dto.AuthControllerDto.LoginResponseDto;

public interface AuthService {
    ResponseEntity<LoginResponseDto> processLogin(String username, String password);

    ResponseEntity<OkDto> processReAuthorization(String refreshToken);

}
