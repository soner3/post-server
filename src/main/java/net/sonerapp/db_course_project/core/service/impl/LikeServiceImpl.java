package net.sonerapp.db_course_project.core.service.impl;

import org.springframework.stereotype.Service;

import net.sonerapp.db_course_project.core.exceptions.EntityNotFoundException;
import net.sonerapp.db_course_project.core.model.Likes;
import net.sonerapp.db_course_project.core.model.Post;
import net.sonerapp.db_course_project.core.model.Profile;
import net.sonerapp.db_course_project.core.model.User;
import net.sonerapp.db_course_project.core.repository.LikeRepository;
import net.sonerapp.db_course_project.core.repository.PostRepository;
import net.sonerapp.db_course_project.core.repository.ProfileRepository;
import net.sonerapp.db_course_project.core.service.LikeService;
import net.sonerapp.db_course_project.core.service.UserService;

@Service
public class LikeServiceImpl implements LikeService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;
    private final LikeRepository likeRepository;

    public LikeServiceImpl(UserService userService, PostRepository postRepository, ProfileRepository profileRepository,
            LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.profileRepository = profileRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public Likes createLike(String username, String comment, long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No post found with the given id"));
        User user = userService.getUser(username);
        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("No profile found for the user"));
        Likes like = new Likes(comment, post, profile);
        return likeRepository.save(like);
    }
}
