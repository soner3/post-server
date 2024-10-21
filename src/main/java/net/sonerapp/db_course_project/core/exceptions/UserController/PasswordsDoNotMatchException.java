package net.sonerapp.db_course_project.core.exceptions.UserController;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
