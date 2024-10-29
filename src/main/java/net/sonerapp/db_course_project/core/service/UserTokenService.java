package net.sonerapp.db_course_project.core.service;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;

import net.sonerapp.db_course_project.core.model.UserToken;

public interface UserTokenService {
    Stream<UserToken> getUserTokenPage(Pageable pageable);
}
