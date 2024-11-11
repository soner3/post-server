package net.sonerapp.db_course_project.core.service.impl;

import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sonerapp.db_course_project.core.exceptions.EntityNotFoundException;
import net.sonerapp.db_course_project.core.exceptions.NoEntityDeletedException;
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

    @Override
    @Transactional
    public void deleteToken(String token) {
        int deletedEntityCount = userTokenRepository.deleteByToken(token);

        if (deletedEntityCount > 0) {
            return;
        } else {
            throw new NoEntityDeletedException("No entity found to delete");
        }
    }

    @Override
    public UserToken getToken(String token) {
        return userTokenRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("No User Token Entity found with the given token"));
    }

}
