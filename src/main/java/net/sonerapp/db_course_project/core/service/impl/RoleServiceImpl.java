package net.sonerapp.db_course_project.core.service.impl;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sonerapp.db_course_project.core.exceptions.EntityNotFoundException;
import net.sonerapp.db_course_project.core.exceptions.IllegalUuidException;
import net.sonerapp.db_course_project.core.exceptions.NoEntityDeletedException;
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

    @Override
    @Transactional
    public void deleteRole(String uuid) {
        UUID newUuid = null;
        try {
            newUuid = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new IllegalUuidException("Could not convert UUID-String to UUID-Type");
        }

        int deletedEntityCount = roleRepository.deleteByUuid(newUuid);

        if (deletedEntityCount > 0) {
            return;
        } else {
            throw new NoEntityDeletedException("No entity found to delete");
        }

    }

    @Override
    public Role getRole(String uuid) {
        UUID newUuid = null;
        try {
            newUuid = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new IllegalUuidException("Could not convert UUID-String to UUID-Type");
        }

        return roleRepository.findByUuid(newUuid)
                .orElseThrow(() -> new EntityNotFoundException("No User Token found to delete"));

    }

}
