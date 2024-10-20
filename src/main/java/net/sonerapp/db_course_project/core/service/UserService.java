package net.sonerapp.db_course_project.core.service;

import net.sonerapp.db_course_project.core.model.User;

public interface UserService {

    User createUser(String username, String email, String password, String firstname, String lastname);

    User createAdminUser(String username, String email, String password, String firstname, String lastname);

    void activateUser(String token);

}
