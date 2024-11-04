package net.sonerapp.db_course_project;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import net.sonerapp.db_course_project.core.model.Profile;
import net.sonerapp.db_course_project.core.repository.ProfileRepository;

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
    private ProfileRepository profiles;

    @Test
    void datasource_jdbctemplate_entitymanager_repository_not_null() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(em).isNotNull();
        assertThat(profiles).isNotNull();
    }

    @Test
    void database_has_tables() {
        List<Profile> profiles = em.createQuery("SELECT p FROM Profile p", Profile.class).getResultList();
        assertThat(profiles).isNotNull();

    }

}
