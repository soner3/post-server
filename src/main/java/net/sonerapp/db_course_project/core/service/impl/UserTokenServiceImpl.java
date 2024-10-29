package net.sonerapp.db_course_project.core.service.impl;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.sonerapp.db_course_project.core.model.UserToken;
import net.sonerapp.db_course_project.core.repository.UserTokenRepository;
import net.sonerapp.db_course_project.core.service.UserTokenService;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    private final UserTokenRepository userTokenRepository;

    public UserTokenServiceImpl(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    @Override
    public Stream<UserToken> getUserTokenPage(Pageable pageable) {
        return userTokenRepository.findAll(pageable).stream();
    }

}
