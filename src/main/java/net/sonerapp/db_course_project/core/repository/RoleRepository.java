package net.sonerapp.db_course_project.core.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sonerapp.db_course_project.core.model.Role;
import net.sonerapp.db_course_project.core.model.model_enums.AppRoles;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    boolean existsByRolename(AppRoles rolename);

    Optional<Role> findByRolename(AppRoles rolename);

    Optional<Role> findByUuid(UUID uuid);

    int deleteByUuid(UUID uuid);

}
