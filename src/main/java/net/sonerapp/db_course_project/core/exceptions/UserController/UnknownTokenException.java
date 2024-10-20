package net.sonerapp.db_course_project.core.exceptions.UserController;

public class UnknownTokenException extends RuntimeException {

    public UnknownTokenException(String message) {
        super(message);
    }

}
