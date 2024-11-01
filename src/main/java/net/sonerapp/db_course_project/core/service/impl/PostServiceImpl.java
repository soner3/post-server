package net.sonerapp.db_course_project.core.service.impl;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sonerapp.db_course_project.core.exceptions.EntityNotFoundException;
import net.sonerapp.db_course_project.core.exceptions.IllegalUuidException;
import net.sonerapp.db_course_project.core.exceptions.NoEntityDeletedException;
import net.sonerapp.db_course_project.core.exceptions.PostController.InvalidPostOwnerException;
import net.sonerapp.db_course_project.core.model.Post;
import net.sonerapp.db_course_project.core.model.Profile;
import net.sonerapp.db_course_project.core.model.User;
import net.sonerapp.db_course_project.core.repository.PostRepository;
import net.sonerapp.db_course_project.core.repository.ProfileRepository;
import net.sonerapp.db_course_project.core.service.PostService;
import net.sonerapp.db_course_project.core.service.UserService;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final ProfileRepository profileRepository;

    public PostServiceImpl(PostRepository postRepository, UserService userService,
            ProfileRepository profileRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.profileRepository = profileRepository;
    }

    @Override
    public Post createPost(String msg, UserDetails userDetails) {
        User user = userService.getUser(userDetails.getUsername());
        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new UsernameNotFoundException("No profile found for the user"));
        Post post = new Post(msg, profile);
        return postRepository.save(post);
    }

    @Override
    public Stream<Post> getPostList(Pageable pageable) {
        return postRepository.findAll(pageable).stream();
    }

    @Override
    @Transactional
    public void deletePost(String uuid, UserDetails userDetails) {
        UUID newUuid = UUID.randomUUID();
        try {
            newUuid = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new IllegalUuidException("Could not convert UUID-String to UUID-Type");
        }
        User user = userService.getUser(userDetails.getUsername());
        Profile userProfile = profileRepository.findByUser(user)
                .orElseThrow(() -> new UsernameNotFoundException("No profile found for the user"));

        Post post = postRepository.findByUuid(newUuid)
                .orElseThrow(() -> new EntityNotFoundException("No post found with the given UUID"));

        Profile postProfile = post.getProfile();

        if (postProfile.getUuid().equals(userProfile.getUuid())) {
            int deletetEntityCount = postRepository.deleteByUuid(newUuid);

            if (deletetEntityCount > 0) {
                return;
            } else {
                throw new NoEntityDeletedException("No entity found to delete");
            }
        } else {
            throw new InvalidPostOwnerException("The user is not owner of the post");
        }

    }

}
