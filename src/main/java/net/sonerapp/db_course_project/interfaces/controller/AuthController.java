package net.sonerapp.db_course_project.interfaces.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.OkDto;
import net.sonerapp.db_course_project.application.dto.AuthControllerDto.LoginRequestDto;
import net.sonerapp.db_course_project.application.dto.AuthControllerDto.LoginResponseDto;
import net.sonerapp.db_course_project.application.service.AuthService;
import net.sonerapp.db_course_project.infrastructure.security.jwt.JwtUtils;

@RestController
@RequestMapping("/api/auth/jwt")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/create")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginData) {
        return authService.processLogin(loginData.username(), loginData.password());
    }

    @PostMapping("/refresh")
    public ResponseEntity<OkDto> reAuthorize(
            @CookieValue(required = true, name = JwtUtils.REFRESH_COOKIE_KEY) String refreshToken) {
        return authService.processReAuthorization(refreshToken);
    }

    @ExceptionHandler
    public ResponseEntity<?> authError(AuthenticationException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problem.setTitle("Authentication Failed");
        problem.setDetail(e.getMessage());
        return ResponseEntity.of(problem).build();

    }

}
