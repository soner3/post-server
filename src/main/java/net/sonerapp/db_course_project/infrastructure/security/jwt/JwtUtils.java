package net.sonerapp.db_course_project.infrastructure.security.jwt;

import java.security.KeyPair;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Getter
public class JwtUtils {

    @Value("${jwt.refresh.expiration}")
    private int refreshExpiryTime;

    @Value("${jwt.access.expiration}")
    private int accessExpiryTime;

    private final String HEADER_TYPE_KEY = "typ";
    private final String HEADER_TYPE_VALUE = "JWT";

    private final String TOKEN_TYPE_KEY = "type";
    private final String TOKEN_TYPE_ACCESS = "access";
    private final String TOKEN_TYPE_REFRESH = "refresh";

    private final String ACCESS_COOKIE_KEY = "accessToken";
    private final String REFRESH_COOKIE_KEY = "refreshToken";

    // Nur für entwicklungs zwecke
    private final KeyPair keyPair = Jwts.SIG.RS512.keyPair().build();

    public String getJwtFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Auhtorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        } else {
            return null;
        }
    }

    private String createJwtToken(String username, String tokenType, int expiration) {
        return Jwts
                .builder()
                .subject(username)
                .header()
                .add(HEADER_TYPE_KEY, HEADER_TYPE_VALUE)
                .and()
                .claim(TOKEN_TYPE_KEY, tokenType)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + expiration))
                .signWith(keyPair.getPrivate())
                .compact();
    }

    public String createRefreshToken(UserDetails userDetails) {
        return createJwtToken(userDetails.getUsername(), TOKEN_TYPE_REFRESH, refreshExpiryTime);
    }

    public String getUsernameFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(keyPair.getPublic())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateRefreshToken(String refreshToken) {
        try {
            String tokenType = Jwts
                    .parser()
                    .verifyWith(keyPair.getPublic())
                    .build()
                    .parseSignedClaims(refreshToken)
                    .getPayload()
                    .get(TOKEN_TYPE_KEY, String.class);

            if (tokenType != null && tokenType.equals(TOKEN_TYPE_REFRESH)) {
                return true;
            } else {
                return false;
            }
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Token is Expired", e.getMessage());

        } catch (UnsupportedJwtException e) {
            log.error("JWT token is not supported", e.getMessage());

        } catch (IllegalArgumentException e) {
            log.error("JWT Claims string is empty", e.getMessage());
        }

        return false;

    }

    public boolean validateAccessToken(String accessToken) {
        try {
            String tokenType = Jwts
                    .parser()
                    .verifyWith(keyPair.getPublic())
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload()
                    .get(TOKEN_TYPE_KEY, String.class);

            if (tokenType != null && tokenType.equals(TOKEN_TYPE_ACCESS)) {
                return true;
            } else {
                return false;
            }
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Token is Expired", e.getMessage());

        } catch (UnsupportedJwtException e) {
            log.error("JWT token is not supported", e.getMessage());

        } catch (IllegalArgumentException e) {
            log.error("JWT Claims string is empty", e.getMessage());
        }

        return false;
    }

    public String generateAccessTokenFromRefreshToken(String username, String refreshToken) {
        if (refreshToken != null && validateRefreshToken(refreshToken)) {
            return createJwtToken(username, TOKEN_TYPE_ACCESS, accessExpiryTime);
        } else {
            return null;
        }
    }

}
