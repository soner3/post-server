package net.sonerapp.db_course_project.interfaces.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import net.sonerapp.db_course_project.application.dto.RoleController.RoleDto;
import net.sonerapp.db_course_project.core.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper extends Converter<Role, RoleDto> {

    RoleDto convert(@NonNull Role role);
}
