package net.sonerapp.db_course_project.interfaces.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import net.sonerapp.db_course_project.application.dto.ProfileController.ProfileDto;
import net.sonerapp.db_course_project.core.model.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper extends Converter<Profile, ProfileDto> {
    ProfileDto convert(@NonNull Profile profile);
}
