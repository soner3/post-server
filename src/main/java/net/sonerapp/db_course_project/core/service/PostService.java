package net.sonerapp.db_course_project.core.service;


import org.springframework.security.core.userdetails.UserDetails;

import net.sonerapp.db_course_project.core.model.Post;

public interface PostService {

    Post createPost(String msg, UserDetails userDetails);
}
