package net.sonerapp.db_course_project.application.exceptions;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String message) {
        super(message);
    }

}
