package pl.lepa.crudapp.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pl.lepa.crudapp.exceptions.IncorrectJWT;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


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
        } catch (JwtException e) {
            throw new IncorrectJWT("Incorrect token: "+token);
        }
    }

    public String getJwtSubject(String token) {
        return jwtClaims(token).getBody().getSubject();
    }

    public Set<SimpleGrantedAuthority> getAuthority(String token) {

        var authorities = (List<Map<String, String>>) jwtClaims(token).getBody().get("authorities");
        return authorities.stream().map(a -> new SimpleGrantedAuthority(a.get("authority"))).collect(Collectors.toSet());
    }


}
