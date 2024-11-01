package net.sonerapp.db_course_project.core.exceptions;

public class NoEntityDeletedException extends RuntimeException {

    public NoEntityDeletedException(String message) {
        super(message);
    }

}
