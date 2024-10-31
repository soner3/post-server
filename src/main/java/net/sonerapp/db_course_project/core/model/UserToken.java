package net.sonerapp.db_course_project.core.model;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.sonerapp.db_course_project.core.model.model_enums.UserTokenType;

@Entity
@ToString
@EqualsAndHashCode
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String token;

    @Column(nullable = false, updatable = false)
    private Instant expiryDate;

    @Column(nullable = false)
    private boolean used;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private UserTokenType tokenType;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User user;

    public UserToken() {
    }

    public UserToken(String token, User user, UserTokenType tokenType) {
        this.token = token;
        this.user = user;
        this.tokenType = tokenType;
        this.used = false;
        this.expiryDate = Instant.now().plus(15, ChronoUnit.MINUTES);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public UserTokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(UserTokenType tokenType) {
        this.tokenType = tokenType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
