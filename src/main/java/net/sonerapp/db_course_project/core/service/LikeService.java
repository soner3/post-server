package net.sonerapp.db_course_project.core.service;

import net.sonerapp.db_course_project.core.model.Likes;;

public interface LikeService {
   Likes createLike(String username, String comment, String uuid);

}
