package net.sonerapp.db_course_project.application.exceptions;

public class UserDeactivatedException extends IllegalArgumentException {
    public UserDeactivatedException(String message) {
        super(message);
    }
}
