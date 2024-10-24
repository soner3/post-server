package net.sonerapp.db_course_project.core.exceptions.UserController;

public class PasswordIsNullException extends NullPointerException {

    public PasswordIsNullException(String message) {
        super(message);
    }
}
