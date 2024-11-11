package net.sonerapp.db_course_project.core.service;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import net.sonerapp.db_course_project.core.model.Likes;;

public interface LikeService {
   Likes createLike(String username, String uuid);

   void deleteLike(String postUuid, UserDetails userDetails);

   Stream<Likes> getAllLikes(Pageable pageable);

}
