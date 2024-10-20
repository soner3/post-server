package net.sonerapp.db_course_project.application.exceptions;

public class TokenAlreadyUsedException extends RuntimeException {

    public TokenAlreadyUsedException(String message) {
        super(message);
    }

}
