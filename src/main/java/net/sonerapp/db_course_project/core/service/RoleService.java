package net.sonerapp.db_course_project.core.service;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;

import net.sonerapp.db_course_project.core.model.Role;

public interface RoleService {

    Stream<Role> getRolePage(Pageable pageable);

}
