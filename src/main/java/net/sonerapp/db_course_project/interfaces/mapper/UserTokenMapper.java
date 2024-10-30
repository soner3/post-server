package net.sonerapp.db_course_project.interfaces.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import net.sonerapp.db_course_project.application.dto.UserTokenControllerDto.UserTokenDto;
import net.sonerapp.db_course_project.core.model.UserToken;

@Mapper(componentModel = "spring")
public interface UserTokenMapper extends Converter<UserToken, UserTokenDto> {

    @Mapping(source = "used", target = "isUsed")
    UserTokenDto convert(@NonNull UserToken userToken);

}
