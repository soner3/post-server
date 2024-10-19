package net.sonerapp.db_course_project.core.event.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.sonerapp.db_course_project.core.model.Profile;
import net.sonerapp.db_course_project.core.model.User;
import net.sonerapp.db_course_project.core.model.UserToken;
import net.sonerapp.db_course_project.core.model.model_enums.UserTokenType;
import net.sonerapp.db_course_project.infrastructure.email.EmailService;
import net.sonerapp.db_course_project.infrastructure.repository.ProfileRepository;
import net.sonerapp.db_course_project.infrastructure.repository.UserTokenRepository;

@Service
@Slf4j
public class UserListener {

    private final ProfileRepository profileRepository;

    private final EmailService emailService;

    private final UserTokenRepository userTokenRepository;

    @Value("${frontend.url}")
    private String frontendUrl;

    public UserListener(ProfileRepository profileRepository, EmailService emailService,
            UserTokenRepository userTokenRepository) {
        this.profileRepository = profileRepository;
        this.emailService = emailService;
        this.userTokenRepository = userTokenRepository;
    }

    @EventListener
    @Async
    public void createProfileFromUser(UserCreatedEvent event) {
        User user = event.user();
        Profile newProfile = new Profile(user);

        profileRepository.save(newProfile);
        log.info("Profile created for User: {}", user.getFirstname() + " " + user.getLastname());

        String token = UUID.randomUUID().toString();
        UserToken userToken = new UserToken(token, user, UserTokenType.USER_ACTIVATION_TOKEN);

        UserToken createdUserToken = userTokenRepository.save(userToken);

        String activateUrl = frontendUrl + "/" + createdUserToken.getToken();

        emailService.sendActivateUserMail(user.getEmail(), activateUrl);

    }

}
