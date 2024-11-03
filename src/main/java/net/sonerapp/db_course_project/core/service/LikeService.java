package net.sonerapp.db_course_project.core.service;

import org.springframework.security.core.userdetails.UserDetails;

import net.sonerapp.db_course_project.core.model.Likes;;

public interface LikeService {
   Likes createLike(String username, String uuid);

   void deleteLike(String postUuid, UserDetails userDetails);

}
