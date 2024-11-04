package net.sonerapp.db_course_project.core.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sonerapp.db_course_project.core.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByUuid(UUID uuid);

    int deleteByUuid(UUID uuid);

}
