package net.sonerapp.db_course_project.core.service.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.sonerapp.db_course_project.core.event.user.UserCreatedEvent;
import net.sonerapp.db_course_project.core.exceptions.UserController.EmailExistsException;
import net.sonerapp.db_course_project.core.exceptions.UserController.UsernameExistsException;
import net.sonerapp.db_course_project.core.model.Role;
import net.sonerapp.db_course_project.core.model.User;
import net.sonerapp.db_course_project.core.model.model_enums.AppRoles;
import net.sonerapp.db_course_project.core.service.UserService;
import net.sonerapp.db_course_project.infrastructure.repository.RoleRepository;
import net.sonerapp.db_course_project.infrastructure.repository.UserRepository;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher publisher;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder, ApplicationEventPublisher publisher) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.publisher = publisher;
    }

    @Override
    public User createUser(String username, String email, String password, String firstname, String lastname) {

        if (userRepository.existsByUsername(username)) {
            throw new UsernameExistsException("The given username already exists.");
        }

        if (userRepository.existsByEmail(email)) {
            throw new EmailExistsException("The given email already exists.");
        }

        Role userRole = roleRepository.findByRolename(AppRoles.ROLE_USER)
                .orElseGet(() -> roleRepository.save(new Role(AppRoles.ROLE_USER)));

        User user = new User(username, email, passwordEncoder.encode(password), firstname, lastname);

        user.setRole(userRole);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(false);

        User createdUser = userRepository.save(user);
        log.info("User created for {}", createdUser.getFirstname() + " " + createdUser.getLastname());

        publisher.publishEvent(new UserCreatedEvent(createdUser));

        return createdUser;
    }

    @Override
    public User createAdminUser(String username, String email, String password, String firstname, String lastname) {
        Role adminRole = roleRepository.findByRolename(AppRoles.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(new Role(AppRoles.ROLE_ADMIN)));

        User adminUser = new User(username, email, passwordEncoder.encode(password), firstname, lastname);

        adminUser.setRole(adminRole);
        adminUser.setAccountNonExpired(true);
        adminUser.setAccountNonLocked(true);
        adminUser.setCredentialsNonExpired(true);
        adminUser.setEnabled(true);

        User createdUser = userRepository.save(adminUser);
        log.info("Admin User created for {}", createdUser.getFirstname() + " " + createdUser.getLastname());

        return createdUser;
    }

}
