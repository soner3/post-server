package net.sonerapp.db_course_project.core.exceptions.UserController;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String message) {
        super(message);
    }

}
