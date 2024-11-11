package net.sonerapp.db_course_project.core.service.impl;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.sonerapp.db_course_project.core.exceptions.EntityNotFoundException;
import net.sonerapp.db_course_project.core.exceptions.IllegalUuidException;
import net.sonerapp.db_course_project.core.exceptions.InvalidOwnerException;
import net.sonerapp.db_course_project.core.exceptions.NoEntityDeletedException;
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
        UUID newUuid = null;
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

    @Override
    @Transactional
    public void deleteComment(String commentUuid, UserDetails userDetails) {
        UUID newCommentUuid = null;
        try {
            newCommentUuid = UUID.fromString(commentUuid);

        } catch (IllegalArgumentException e) {
            throw new IllegalUuidException("Could not convert UUID-String to UUID-Type");
        }

        User user = userService.getUser(userDetails.getUsername());

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("No Profile found for the logged in User"));

        Comment comment = commentRepository.findByUuid(newCommentUuid)
                .orElseThrow(() -> new EntityNotFoundException("No comment found with the given comment UUID"));

        if (comment.getProfile().equals(profile)) {
            int deletedEntityCount = commentRepository.deleteByUuid(newCommentUuid);

            if (deletedEntityCount > 0) {
                return;
            } else {
                throw new NoEntityDeletedException("No entity found to delete");
            }
        } else {
            throw new InvalidOwnerException("The Profile is not owner of this comment");
        }

    }

    @Override
    public Stream<Comment> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable).stream();
    }

}
