package net.sonerapp.db_course_project.core.service;

import net.sonerapp.db_course_project.core.model.Like;

public interface LikeService {
   Like createLike(String username,String comment, long id);

}
