package net.sonerapp.db_course_project.infrastructure.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import net.sonerapp.db_course_project.core.service.UserService;
import net.sonerapp.db_course_project.infrastructure.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 2, 1 << 16, 3);
    }

    @Bean
    CommandLineRunner initData(UserRepository userRepository, UserService userService) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                userService.createAdminUser("admin", "admin@admin.com", "adminpassword", "Admin", "Test");
            }
            if (!userRepository.existsByUsername("user")) {
                userService.createUser("user", "user@user.com", "userpassword", "User", "Test");
            }
        };
    }

}
