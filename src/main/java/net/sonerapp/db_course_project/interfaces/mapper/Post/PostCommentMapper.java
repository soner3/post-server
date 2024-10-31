package net.sonerapp.db_course_project.interfaces.mapper.Post;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import net.sonerapp.db_course_project.application.dto.PostController.PostCommentDto;
import net.sonerapp.db_course_project.core.model.Comment;

@Mapper(componentModel = "spring")
public interface PostCommentMapper extends Converter<Comment, PostCommentDto> {

    PostCommentDto convert(@NonNull Comment comment);

}
