package net.sonerapp.db_course_project.application.exceptions;

public class UserNotAuthenticatedException extends NullPointerException {

    public UserNotAuthenticatedException(String message) {
        super(message);
    }

}
