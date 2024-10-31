package net.sonerapp.db_course_project.interfaces.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import net.sonerapp.db_course_project.application.dto.LikeControllerDto.LikeDto;
import net.sonerapp.db_course_project.core.model.Likes;

@Mapper(componentModel = "spring")
public interface LikeMapper extends Converter<Likes, LikeDto> {

    LikeDto convert(@NonNull Likes likes);

}
