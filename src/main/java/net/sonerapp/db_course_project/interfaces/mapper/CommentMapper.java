package net.sonerapp.db_course_project.interfaces.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import net.sonerapp.db_course_project.application.dto.CommentControllerDto.CommentDto;
import net.sonerapp.db_course_project.core.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper extends Converter<Comment, CommentDto> {

    CommentDto convert(@NonNull Comment comment);

}
