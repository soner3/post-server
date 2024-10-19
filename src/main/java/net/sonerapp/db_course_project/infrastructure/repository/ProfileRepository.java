package net.sonerapp.db_course_project.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sonerapp.db_course_project.core.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
