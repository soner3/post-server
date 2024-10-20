package net.sonerapp.db_course_project.core.exceptions.UserController;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String message) {
        super(message);
    }

}
