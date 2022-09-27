package gwshin.dlink.security.jwt;

import gwshin.dlink.security.services.AdminUserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    @Value("${gwshin.app.jwtSecret}")
    private String jwtSecret;

    @Value("${gwshin.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        AdminUserDetailsImpl userPrincipal = (AdminUserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUserIdFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid Jwt signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid Jwt token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Invalid Jwt is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Invalid Jwt is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Jwt claims string is empty: {}", e.getMessage());
        } catch (NullPointerException e) {
            log.error("Cannot set user authentication: {}", e.getMessage());
        }

        return false;
    }

}
