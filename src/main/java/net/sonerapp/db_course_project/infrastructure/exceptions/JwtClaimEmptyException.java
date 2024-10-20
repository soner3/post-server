package net.sonerapp.db_course_project.infrastructure.exceptions;

public class JwtClaimEmptyException extends IllegalArgumentException {

    public JwtClaimEmptyException(String message) {
        super(message);
    }
}
