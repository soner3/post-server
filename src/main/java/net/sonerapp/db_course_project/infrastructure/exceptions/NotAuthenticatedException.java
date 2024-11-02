package net.sonerapp.db_course_project.infrastructure.exceptions;

public class NotAuthenticatedException extends RuntimeException {

    public NotAuthenticatedException(String message) {
        super(message);
    }

}
