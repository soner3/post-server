package net.sonerapp.db_course_project.infrastructure.exceptions;

import org.springframework.security.core.AuthenticationException;

public class NotAuthenticatedException extends AuthenticationException {

    public NotAuthenticatedException(String message) {
        super(message);
    }

}
