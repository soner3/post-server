package net.sonerapp.db_course_project.core.exceptions;

public class EntityNotFoundException extends IllegalArgumentException {

    public EntityNotFoundException(String message) {
        super(message);
    }
    
}
