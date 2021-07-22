package pl.lepa.crudapp.configuration.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.jwt")
public class JwtConfig {

    private String secret;
    private int expirationTime;
    private String tokenPrefix;
    private String headerString;

}
