package net.sonerapp.db_course_project.application.exceptions;

public class NoStrongPasswordException extends RuntimeException {

    public NoStrongPasswordException(String message) {
        super(message);
    }

}
