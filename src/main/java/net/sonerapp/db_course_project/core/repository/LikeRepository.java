package net.sonerapp.db_course_project.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sonerapp.db_course_project.core.model.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
}
