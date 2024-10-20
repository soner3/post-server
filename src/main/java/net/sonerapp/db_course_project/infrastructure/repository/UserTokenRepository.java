package net.sonerapp.db_course_project.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sonerapp.db_course_project.core.model.UserToken;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    Optional<UserToken> findByToken(String token);

}
