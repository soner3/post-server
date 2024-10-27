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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.OkDto;
import net.sonerapp.db_course_project.application.dto.AuthControllerDto.LoginRequestDto;
import net.sonerapp.db_course_project.application.dto.AuthControllerDto.LoginResponseDto;
import net.sonerapp.db_course_project.application.exceptions.UserDoesNotExistException;
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
    @Operation(summary = "Authenticate user and return JWT access and refresh token", description = "Authenticates the user using username and password. Returns a JWT access and refresh Token for the user as http-only cookies. The access token expires in 10min and the refresh in 2d.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successfull", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failed due to invalid login credentials", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Authentication Failed because the user is not enabled", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))

    })
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginData) {
        return authService.processLogin(loginData.username(), loginData.password());
    }

    @PostMapping("/refresh")
    public ResponseEntity<OkDto> reAuthorize(
            @CookieValue(required = true, name = JwtUtils.REFRESH_COOKIE_KEY) String refreshToken) {
        return authService.processReAuthorization(refreshToken);
    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> authError(AuthenticationException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problem.setTitle("Authentication Failed");
        problem.setDetail(e.getMessage());
        return ResponseEntity.of(problem).build();

    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> noUser(UserDoesNotExistException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problem.setTitle("No User Found");
        problem.setDetail(e.getMessage());
        return ResponseEntity.of(problem).build();

    }

}
