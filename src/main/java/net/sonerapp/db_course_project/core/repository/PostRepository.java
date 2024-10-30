package net.sonerapp.db_course_project.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sonerapp.db_course_project.core.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    
}
