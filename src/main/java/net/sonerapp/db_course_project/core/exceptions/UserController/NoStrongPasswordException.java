package net.sonerapp.db_course_project.core.exceptions.UserController;

public class NoStrongPasswordException extends RuntimeException {

    public NoStrongPasswordException(String message) {
        super(message);
    }

}
