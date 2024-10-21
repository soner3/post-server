package net.sonerapp.db_course_project.core.event.user;

import net.sonerapp.db_course_project.core.model.User;

public record ResendActivationMailEvent(User user) {

}
