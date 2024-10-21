package net.sonerapp.db_course_project.core.exceptions.UserController;

public class EmailDoesNotExistException extends RuntimeException {

    public EmailDoesNotExistException(String message) {
        super(message);
    }

}
