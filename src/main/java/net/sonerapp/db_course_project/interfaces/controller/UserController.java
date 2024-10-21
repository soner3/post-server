package net.sonerapp.db_course_project.interfaces.controller;

import java.util.stream.Stream;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.OkDto;
import net.sonerapp.db_course_project.application.dto.UserControllerDto.ActivateUserDto;
import net.sonerapp.db_course_project.application.dto.UserControllerDto.CreateUserDto;
import net.sonerapp.db_course_project.application.dto.UserControllerDto.UserDto;
import net.sonerapp.db_course_project.core.exceptions.UserController.EmailExistsException;
import net.sonerapp.db_course_project.core.exceptions.UserController.InvalidUserTokenTypeException;
import net.sonerapp.db_course_project.core.exceptions.UserController.NoStrongPasswordException;
import net.sonerapp.db_course_project.core.exceptions.UserController.TokenAlreadyUsedException;
import net.sonerapp.db_course_project.core.exceptions.UserController.TokenExpiredException;
import net.sonerapp.db_course_project.core.exceptions.UserController.UnknownTokenException;
import net.sonerapp.db_course_project.core.exceptions.UserController.UsernameExistsException;
import net.sonerapp.db_course_project.core.model.User;
import net.sonerapp.db_course_project.core.service.UserService;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    private final UserService userService;

    private final ConversionService conversionService;

    public UserController(UserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @PostMapping("/public/create")
    public ResponseEntity<OkDto> createUser(@RequestBody @Valid CreateUserDto userData) {
        userService.createUser(userData.username(), userData.email(), userData.password(), userData.firstname(),
                userData.lastname());
        return ResponseEntity.ok(new OkDto("User created successfully"));
    }

    @PostMapping("/public/activate")
    public ResponseEntity<OkDto> activateUser(@RequestBody @Valid ActivateUserDto tokenData) {
        userService.activateUser(tokenData.token());
        return ResponseEntity.ok(new OkDto("User activated successfully"));
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Stream<UserDto>> getUserList(Pageable pageable) {
        Stream<UserDto> userStream = userService.getUserPage(pageable)
                .map(user -> conversionService.convert(user, UserDto.class));
        return ResponseEntity.ok(userStream);
    }

    @GetMapping
    public ResponseEntity<UserDto> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUser(userDetails.getUsername());
        UserDto userDto = conversionService.convert(user, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping
    public ResponseEntity<OkDto> deleteUser(@AuthenticationPrincipal UserDetails userdDetails) {
        userService.deleteUser(userdDetails.getUsername());
        return ResponseEntity.ok(new OkDto("User deleted successfully"));
    }

    @ExceptionHandler
    public ResponseEntity<?> emailExists(EmailExistsException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Email already exists");
        problem.setDetail(e.getMessage());
        return ResponseEntity.of(problem).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> usernameExists(UsernameExistsException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Username already exists");
        problem.setDetail(e.getMessage());
        return ResponseEntity.of(problem).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> unknownToken(UnknownTokenException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Unknown Token");
        problem.setDetail(e.getMessage());
        return ResponseEntity.of(problem).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> tokenExpiryError(TokenExpiredException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Token Expired");
        problem.setDetail(e.getMessage());

        return ResponseEntity.of(problem).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> weakPassword(NoStrongPasswordException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Weak Password");
        problem.setDetail(e.getMessage());
        return ResponseEntity.of(problem).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> tokenUsed(TokenAlreadyUsedException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Used Token");
        problem.setDetail(e.getMessage());
        return ResponseEntity.of(problem).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> invalidToken(InvalidUserTokenTypeException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Invalid Token");
        problem.setDetail(e.getMessage());
        return ResponseEntity.of(problem).build();
    }

}
