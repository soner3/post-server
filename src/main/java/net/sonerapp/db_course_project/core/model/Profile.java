package net.sonerapp.db_course_project.core.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sonerapp.db_course_project.core.model.model_enums.Gender;

@Data
@Entity
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender = Gender.DIVERSE;

    private String country;

    private String street;

    private int streetNumber;

    private int zipCode;

    private String city;

    @OneToOne
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Profile(User user) {
        this.user = user;
        this.uuid = UUID.randomUUID();
    }

}
