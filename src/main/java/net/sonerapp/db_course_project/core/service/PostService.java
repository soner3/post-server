package net.sonerapp.db_course_project.core.service;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import net.sonerapp.db_course_project.core.model.Post;

public interface PostService {

    Post createPost(String msg, UserDetails userDetails);

    Stream<Post> getPostList(Pageable pageable);
}
