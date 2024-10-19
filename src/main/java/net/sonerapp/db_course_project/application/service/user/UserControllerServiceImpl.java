package net.sonerapp.db_course_project.application.service.user;

import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.sonerapp.db_course_project.application.dto.UserControllerDto.CreateUserDto;
import net.sonerapp.db_course_project.application.exceptions.NoStrongPasswordException;
import net.sonerapp.db_course_project.core.service.UserService;

@Service
public class UserControllerServiceImpl implements UserControllerService {

    private final UserService userService;

    public UserControllerServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> createUser(CreateUserDto userData) {

        String password = userData.password();

        if (!isPasswordStrong(password)) {
            throw new NoStrongPasswordException(
                    "your password must contain at least 8 characters and has uppcase, lowercase, digits and special characters.");
        }

        userService.createUser(userData.username(), userData.email(), password, userData.firstname(),
                userData.lastname());
        return ResponseEntity.ok("User created successfully");
    }

    private boolean isPasswordStrong(String password) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        return Pattern.matches(passwordPattern, password);
    }

}
