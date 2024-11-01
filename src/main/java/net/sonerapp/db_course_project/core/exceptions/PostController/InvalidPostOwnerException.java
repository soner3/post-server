package net.sonerapp.db_course_project.core.exceptions.PostController;

public class InvalidPostOwnerException extends RuntimeException {

    public InvalidPostOwnerException(String message) {
        super(message);
    }
}
