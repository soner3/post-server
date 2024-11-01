package net.sonerapp.db_course_project.core.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import net.sonerapp.db_course_project.core.exceptions.EntityNotFoundException;
import net.sonerapp.db_course_project.core.exceptions.IllegalUuidException;
import net.sonerapp.db_course_project.core.model.Comment;
import net.sonerapp.db_course_project.core.model.Post;
import net.sonerapp.db_course_project.core.model.Profile;
import net.sonerapp.db_course_project.core.model.User;
import net.sonerapp.db_course_project.core.repository.CommentRepository;
import net.sonerapp.db_course_project.core.repository.PostRepository;
import net.sonerapp.db_course_project.core.repository.ProfileRepository;
import net.sonerapp.db_course_project.core.service.CommentService;
import net.sonerapp.db_course_project.core.service.UserService;

@Service
public class CommentServiceImpl implements CommentService {

    private final UserService userService;
    private final ProfileRepository profileRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(UserService userService, ProfileRepository profileRepository,
            PostRepository postRepository, CommentRepository commentRepository) {
        this.userService = userService;
        this.profileRepository = profileRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(String username, String uuid, String comment) {
        UUID newUuid = UUID.randomUUID();
        try {
            newUuid = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new IllegalUuidException("Could not convert UUID-String to UUID-Type");
        }

        User user = userService.getUser(username);
        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("No Profile found for user"));
        Post post = postRepository.findByUuid(newUuid)
                .orElseThrow(() -> new EntityNotFoundException("No Post found for this UUID"));
        Comment newComment = new Comment(post, profile, comment);
        return commentRepository.save(newComment);
    }

}
