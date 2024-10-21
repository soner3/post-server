package net.sonerapp.db_course_project.infrastructure.exceptions;

public class UserNotEnabledException extends RuntimeException {

    public UserNotEnabledException(String message) {
        super(message);
    }

}
