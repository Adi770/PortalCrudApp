package pl.lepa.crudapp.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtTokenUtil {

    private final JwtConfig jwtConfig;
    private final SecretKey key;

    @Autowired
    public JwtTokenUtil(JwtConfig jwtConfig, SecretKey key) {
        this.jwtConfig = jwtConfig;
        this.key = key;
    }

    public String createToken(Authentication authentication) {

        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + jwtConfig.getExpirationTime());

        String jwt=Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities().toString())
                .setIssuedAt(currentDate)
                .setExpiration(expiredDate)
                .signWith(key)
                .compact();
        return jwt;
    }

    public Jws<Claims> jwtClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Incorrect token", token));
        }
    }

    public String getJwtSubject(String token) {
        return jwtClaims(token).getBody().getSubject();
    }

    public String getAuthority(String token) {
        return jwtClaims(token).getBody().get("authorities").toString();
    }


}
