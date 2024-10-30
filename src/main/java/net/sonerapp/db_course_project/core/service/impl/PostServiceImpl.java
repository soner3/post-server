package net.sonerapp.db_course_project.core.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import net.sonerapp.db_course_project.core.model.Post;
import net.sonerapp.db_course_project.core.model.User;
import net.sonerapp.db_course_project.core.repository.PostRepository;
import net.sonerapp.db_course_project.core.repository.ProfileRepository;
import net.sonerapp.db_course_project.core.service.PostService;
import net.sonerapp.db_course_project.core.service.UserService;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserService userService;
    private final  ProfileRepository profileRepository;

    

    public PostServiceImpl(PostRepository postRepository, UserService userService,
            ProfileRepository profileRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.profileRepository = profileRepository;
    }



    @Override
    public Post createPost(String msg, UserDetails userDetails) {
        User user = userService.getUser(userDetails.getUsername());
        return null;
    }


}
