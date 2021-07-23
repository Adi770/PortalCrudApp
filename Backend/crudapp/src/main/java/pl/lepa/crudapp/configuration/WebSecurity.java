package pl.lepa.crudapp.configuration;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.lepa.crudapp.configuration.jwt.JwtBasicAuthenticationFilter;
import pl.lepa.crudapp.configuration.jwt.JwtConfig;
import pl.lepa.crudapp.configuration.jwt.JwtTokenUtil;
import pl.lepa.crudapp.configuration.jwt.JwtUsernameAndPasswordFilter;
import pl.lepa.crudapp.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@ConfigurationPropertiesScan
public class WebSecurity extends WebSecurityConfigurerAdapter {


    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public WebSecurity(PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsServiceImpl, JwtTokenUtil jwtTokenUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().and()
                .authorizeRequests().antMatchers(SWAGGER_WHITELIST).permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authenticationFilter())
//                .addFilter(jwtBasicAuthenticationFilter())
        ;

    }

    public JwtBasicAuthenticationFilter jwtBasicAuthenticationFilter() throws Exception {
        return new JwtBasicAuthenticationFilter(authenticationManager(), jwtTokenUtil);
    }

    public JwtUsernameAndPasswordFilter authenticationFilter() throws Exception {
        return new JwtUsernameAndPasswordFilter(authenticationManager(), jwtTokenUtil);
    }

//    public JwtTokenUtil jwtTokenUtil() {
//        return new JwtTokenUtil(jwtConfig);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsServiceImpl);
        return provider;
    }

    //CORS CONFIGURATION
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;

    }


}
