package pl.lepa.crudapp.configuration.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Data
@ConfigurationProperties(prefix = "app.jwt")
public class JwtConfig {

    private String secret;
    private int expirationTime;
    private String tokenPrefix;
    private String headerString;

    @Bean
    public SecretKey secretKey(){
        return Keys.hmacShaKeyFor(getSecret().getBytes(StandardCharsets.UTF_8));
    }

}
