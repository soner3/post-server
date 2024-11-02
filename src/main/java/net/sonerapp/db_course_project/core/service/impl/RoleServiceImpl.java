package net.sonerapp.db_course_project.core.service.impl;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.sonerapp.db_course_project.core.model.Role;
import net.sonerapp.db_course_project.core.repository.RoleRepository;
import net.sonerapp.db_course_project.core.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Stream<Role> getRolePage(Pageable pageable) {
        return roleRepository.findAll(pageable).stream();
    }

}
