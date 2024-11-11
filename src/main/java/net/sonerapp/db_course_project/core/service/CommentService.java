package net.sonerapp.db_course_project.core.service;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import net.sonerapp.db_course_project.core.model.Comment;

public interface CommentService {
    Comment createComment(String username, String uuid, String comment);

    void deleteComment(String commentUuid, UserDetails userDetails);

    Stream<Comment> getAllComments(Pageable pageable);
}
