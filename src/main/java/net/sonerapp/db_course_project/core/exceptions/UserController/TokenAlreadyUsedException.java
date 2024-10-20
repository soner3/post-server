package net.sonerapp.db_course_project.core.exceptions.UserController;

public class TokenAlreadyUsedException extends RuntimeException {

    public TokenAlreadyUsedException(String message) {
        super(message);
    }

}
