package net.sonerapp.db_course_project.interfaces.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.sonerapp.db_course_project.application.dto.UserControllerDto.ActivateUserDto;
import net.sonerapp.db_course_project.application.dto.UserControllerDto.CreateUserDto;
import net.sonerapp.db_course_project.application.exceptions.NoStrongPasswordException;
import net.sonerapp.db_course_project.application.exceptions.TokenAlreadyUsedException;
import net.sonerapp.db_course_project.application.service.user.UserControllerService;
import net.sonerapp.db_course_project.core.exceptions.UserController.EmailExistsException;
import net.sonerapp.db_course_project.core.exceptions.UserController.UsernameExistsException;
import net.sonerapp.db_course_project.core.service.UserService;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    private final UserControllerService userControllerService;

    private final UserService userService;

    public UserController(UserControllerService userControllerService, UserService userService) {
        this.userControllerService = userControllerService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDto userData) {
        return userControllerService.createUser(userData);
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateUser(@RequestBody @Valid ActivateUserDto tokenData) {
        userService.activateUser(tokenData.token());
        return ResponseEntity.ok("User activated successfully");
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
