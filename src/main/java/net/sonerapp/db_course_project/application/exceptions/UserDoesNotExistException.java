package net.sonerapp.db_course_project.application.exceptions;

public class UserDoesNotExistException extends IllegalArgumentException {
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
