package net.sonerapp.db_course_project.application.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import net.sonerapp.db_course_project.application.dto.AuthControllerDto.LoginResponseDto;
import net.sonerapp.db_course_project.application.exceptions.AuthenticationFailedException;
import net.sonerapp.db_course_project.application.service.AuthService;
import net.sonerapp.db_course_project.infrastructure.security.jwt.JwtUtils;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<LoginResponseDto> processLogin(String username, String password) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException("Invalid login credentials");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String refreshToken = jwtUtils.generateRefreshToken(userDetails);
        String accessToken = jwtUtils.generateAccessTokenFromRefreshToken(userDetails.getUsername(), refreshToken);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.SET_COOKIE, generateHttpOnlyCookie(JwtUtils.REFRESH_COOKIE_KEY, refreshToken,
                jwtUtils.getRefreshExpiryTime() / 1000).toString());
        header.add(HttpHeaders.SET_COOKIE, generateHttpOnlyCookie(JwtUtils.ACCESS_COOKIE_KEY, accessToken,
                jwtUtils.getAccessExpiryTime() / 1000).toString());

        LoginResponseDto response = new LoginResponseDto(userDetails.getUsername());

        return ResponseEntity.ok().headers(header).body(response);
    }

    @Override
    public ResponseEntity<String> processReAuthorization(String refreshToken) {
        if (jwtUtils.validateRefreshToken(refreshToken)) {
            String username = jwtUtils.getUsernameFromToken(refreshToken);
            String newAccessToken = jwtUtils.generateAccessTokenFromRefreshToken(username, refreshToken);

            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.SET_COOKIE, generateHttpOnlyCookie(JwtUtils.ACCESS_COOKIE_KEY, newAccessToken,
                    jwtUtils.getAccessExpiryTime() / 1000).toString());

            return ResponseEntity.ok().headers(header).body("Reauthorization successfull");
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    private ResponseCookie generateHttpOnlyCookie(String key, String value, int expiration) {
        return ResponseCookie
                .from(key, value)
                .path("/")
                .secure(false)
                .httpOnly(true)
                .sameSite("Strict")
                .maxAge(expiration)
                .build();

    }

}
