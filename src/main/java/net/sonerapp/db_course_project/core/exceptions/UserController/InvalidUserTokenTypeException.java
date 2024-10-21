package net.sonerapp.db_course_project.core.exceptions.UserController;

public class InvalidUserTokenTypeException extends RuntimeException {

    public InvalidUserTokenTypeException(String message) {
        super(message);
    }

}
