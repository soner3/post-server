package net.sonerapp.db_course_project.interfaces.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.sonerapp.db_course_project.application.exceptions.TokenExpiredException;
import net.sonerapp.db_course_project.application.exceptions.UnknownTokenException;

@ControllerAdvice("net.sonerapp.db_course_project.interfaces")
public class ValidationControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> validateDto(MethodArgumentNotValidException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Field Error");
        Map<String, Object> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            }
        });
        problem.setDetail("Validation failed for the following fields:");
        problem.setProperty("errors", errors);
        return ResponseEntity.of(problem).build();
    }

    @ExceptionHandler
    public ResponseEntity<?> validateTokenExpiry(TokenExpiredException e) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Token Expired");
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

}
