package net.sonerapp.db_course_project.core.service;

import org.springframework.security.core.userdetails.UserDetails;

import net.sonerapp.db_course_project.core.model.Comment;

public interface CommentService {
    Comment createComment(String username, String uuid, String comment);

    void deleteComment(String commentUuid, UserDetails userDetails);
}
