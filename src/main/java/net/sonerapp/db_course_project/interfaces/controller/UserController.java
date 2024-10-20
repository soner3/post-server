package net.sonerapp.db_course_project.interfaces.controller;

import java.util.stream.Stream;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.UserControllerDto.ActivateUserDto;
import net.sonerapp.db_course_project.application.dto.UserControllerDto.CreateUserDto;
import net.sonerapp.db_course_project.application.dto.UserControllerDto.UserDto;
import net.sonerapp.db_course_project.core.exceptions.UserController.EmailExistsException;
import net.sonerapp.db_course_project.core.exceptions.UserController.NoStrongPasswordException;
import net.sonerapp.db_course_project.core.exceptions.UserController.TokenAlreadyUsedException;
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

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDto userData) {
        userService.createUser(userData.username(), userData.email(), userData.password(), userData.firstname(),
                userData.lastname());
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateUser(@RequestBody @Valid ActivateUserDto tokenData) {
        userService.activateUser(tokenData.token());
        return ResponseEntity.ok("User activated successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<Stream<UserDto>> getUserList(Pageable pageable) {
        Stream<UserDto> userStream = userService.getUserPage(pageable)
                .map(user -> conversionService.convert(user, UserDto.class));
        return ResponseEntity.ok(userStream);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        UserDto userDto = conversionService.convert(user, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    // Exceptions
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

}
