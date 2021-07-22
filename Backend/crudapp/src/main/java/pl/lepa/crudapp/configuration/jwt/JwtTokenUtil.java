package pl.lepa.crudapp.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtTokenUtil {

    private JwtConfig jwtConfig;
    private final Key key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));

    @Autowired
    public JwtTokenUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createToken(Authentication authentication) {

        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + jwtConfig.getExpirationTime());

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(currentDate)
                .setExpiration(expiredDate)
                .signWith(key)
                .compact();
    }

    public Jws<Claims> jwtClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (JwtException e){
            throw new IllegalStateException(String.format("Incorrect token", token));
        }
    }

    public String getJwtSubject(String token){
        return jwtClaims(token).getBody().getSubject();
    }

    public String getAuthority(String token){
        return jwtClaims(token).getBody().get("authorities").toString();
    }


}
