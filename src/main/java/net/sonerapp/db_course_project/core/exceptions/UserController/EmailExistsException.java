package net.sonerapp.db_course_project.core.exceptions.UserController;

public class EmailExistsException extends RuntimeException {

    public EmailExistsException(String message) {
        super(message);
    }

}
