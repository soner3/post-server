package net.sonerapp.db_course_project.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sonerapp.db_course_project.core.model.Likes;
import net.sonerapp.db_course_project.core.model.Post;
import net.sonerapp.db_course_project.core.model.Profile;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    boolean existsByProfileAndPost(Profile profile, Post post);

}
