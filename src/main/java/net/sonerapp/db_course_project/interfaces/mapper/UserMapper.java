package net.sonerapp.db_course_project.interfaces.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import net.sonerapp.db_course_project.application.dto.UserControllerDto.UserDto;
import net.sonerapp.db_course_project.core.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends Converter<User, UserDto> {

    UserDto convert(@NonNull User user);

}
