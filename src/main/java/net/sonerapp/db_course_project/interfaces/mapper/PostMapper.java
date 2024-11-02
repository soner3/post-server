package net.sonerapp.db_course_project.interfaces.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import net.sonerapp.db_course_project.application.dto.PostController.PostDto;
import net.sonerapp.db_course_project.core.model.Post;

@Mapper(componentModel = "spring")
public interface PostMapper extends Converter<Post, PostDto> {
    PostDto convert(@NonNull Post post);
}
