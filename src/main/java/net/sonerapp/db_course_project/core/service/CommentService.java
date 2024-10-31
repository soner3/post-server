package net.sonerapp.db_course_project.core.service;

import net.sonerapp.db_course_project.core.model.Comment;

public interface CommentService {
    Comment createComment(String username, String uuid, String comment);
}
