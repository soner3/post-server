package net.sonerapp.db_course_project.interfaces.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import net.sonerapp.db_course_project.application.dto.CommentControllerDto.CommentDto;
import net.sonerapp.db_course_project.application.dto.LikeControllerDto.LikeDto;
import net.sonerapp.db_course_project.application.dto.PostController.PostDto;
import net.sonerapp.db_course_project.application.dto.ProfileController.ProfileDto;
import net.sonerapp.db_course_project.application.dto.UserControllerDto.UserDto;
import net.sonerapp.db_course_project.application.dto.UserTokenControllerDto.UserTokenDto;
import net.sonerapp.db_course_project.core.model.Comment;
import net.sonerapp.db_course_project.core.model.Likes;
import net.sonerapp.db_course_project.core.model.Post;
import net.sonerapp.db_course_project.core.model.Profile;
import net.sonerapp.db_course_project.core.model.User;
import net.sonerapp.db_course_project.core.model.UserToken;

@Service
public class ConversionServiceAdapter {

    private final ConversionService conversionService;

    public ConversionServiceAdapter(@Lazy final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public UserTokenDto mapUserTokenToUserTokenDto(final UserToken source) {
        return (UserTokenDto) conversionService.convert(source, TypeDescriptor.valueOf(UserToken.class),
                TypeDescriptor.valueOf(UserTokenDto.class));
    }

    public UserDto mapUserToUserDto(final User source) {
        return (UserDto) conversionService.convert(source, TypeDescriptor.valueOf(User.class),
                TypeDescriptor.valueOf(UserDto.class));
    }

    public ProfileDto mapProfileToProfileDto(final Profile source) {
        return (ProfileDto) conversionService.convert(source, TypeDescriptor.valueOf(Profile.class),
                TypeDescriptor.valueOf(ProfileDto.class));
    }

    public PostDto mapPostToPostDto(final Post source) {
        return (PostDto) conversionService.convert(source, TypeDescriptor.valueOf(Post.class),
                TypeDescriptor.valueOf(PostDto.class));
    }

    public LikeDto mapLikeToLikeDto(final Likes source) {
        return (LikeDto) conversionService.convert(source, TypeDescriptor.valueOf(Likes.class),
                TypeDescriptor.valueOf(LikeDto.class));
    }

    public CommentDto mapCommentToCommentDto(final Comment source) {
        return (CommentDto) conversionService.convert(source, TypeDescriptor.valueOf(Comment.class),
                TypeDescriptor.valueOf(CommentDto.class));
    }

}
