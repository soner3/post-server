package net.sonerapp.db_course_project;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import net.sonerapp.db_course_project.core.model.Profile;
import net.sonerapp.db_course_project.core.model.Role;
import net.sonerapp.db_course_project.core.model.User;
import net.sonerapp.db_course_project.core.model.model_enums.AppRoles;
import net.sonerapp.db_course_project.core.repository.ProfileRepository;
import net.sonerapp.db_course_project.core.repository.RoleRepository;
import net.sonerapp.db_course_project.core.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
public class ProfileRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager em;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private Role role;
    private User user;

    @BeforeEach
    public void configureTestStart() {
        Role newRole = roleRepository.save(new Role(AppRoles.ROLE_USER));
        User newUser = new User("testuser", "test@user.de", "stdhbsrtbhydr", "Test", "User");
        newUser.setRole(newRole);
        userRepository.save(newUser);
        user = newUser;
        role = newRole;
        assertThat(role).isNotNull();
        assertThat(user).isNotNull();
        profileRepository.save(new Profile(user));

    }

    @Test
    public void datasource_jdbctemplate_entitymanager_repository_not_null() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(em).isNotNull();
        assertThat(profileRepository).isNotNull();
    }

    @Test
    public void database_has_tables() {
        List<Profile> profiles = em.createQuery("SELECT p FROM Profile p", Profile.class).getResultList();
        assertThat(profiles).isNotNull();

    }

    @Test
    public void returns_all_profiles() {
        List<Profile> profiles = profileRepository.findAll();
        for (Profile profile : profiles) {
            assertThat(profile).isNotNull();
            assertThat(profile).isInstanceOf(Profile.class);
        }
    }

    @Test
    public void creates_and_saves_profile() {
        Role newRole = roleRepository.save(new Role(AppRoles.ROLE_USER));
        assertThat(role).isNotNull();

        User newUser = new User("testuser2", "tes2t@user.de", "sbeguibguisbri", "Tesdgrt", "Usersth");
        newUser.setRole(newRole);
        User createdUser = userRepository.save(newUser);
        assertThat(createdUser).isNotNull();

        Profile newProfile = profileRepository.save(new Profile(createdUser));
        assertThat(newProfile).isNotNull();
        Profile maybeCreatedProfile = profileRepository.findById(newProfile.getId()).orElse(null);
        assertThat(maybeCreatedProfile).isNotNull();
        assertThat(maybeCreatedProfile.equals(newProfile)).isTrue();

    }

}
