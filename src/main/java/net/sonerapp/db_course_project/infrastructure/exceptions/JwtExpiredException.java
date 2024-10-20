package net.sonerapp.db_course_project.infrastructure.exceptions;

public class JwtExpiredException extends RuntimeException {

    public JwtExpiredException(String message) {
        super(message);
    }

}
