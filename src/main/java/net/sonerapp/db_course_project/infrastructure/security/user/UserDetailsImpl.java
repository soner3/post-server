package net.sonerapp.db_course_project.infrastructure.security.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import net.sonerapp.db_course_project.core.model.User;

@Service
public class UserDetailsImpl implements UserDetails {

    private String username;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private boolean isEnabled;

    private boolean isCredentialsNonExpired;

    private boolean isAccountNonLocked;

    private boolean isAccountNonExpired;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(String username, String email, String password, String firstname, String lastname,
            boolean isEnabled, boolean isCredentialsNonExpired, boolean isAccountNonLocked, boolean isAccountNonExpired,
            Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isEnabled = isEnabled;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isAccountNonExpired = isAccountNonExpired;
        this.authorities = authorities;
    }

    public static UserDetails build(User user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRolename().toString());
        return new UserDetailsImpl(user.getUsername(), user.getEmail(), user.getPassword(), user.getFirstname(),
                user.getLastname(), user.isEnabled(), user.isCredentialsNonExpired(), user.isAccountNonLocked(),
                user.isAccountNonExpired(), List.of(authority));
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}
