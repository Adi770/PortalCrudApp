package pl.lepa.crudapp.configuration.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class JwtBasicAutheticationFilter extends BasicAuthenticationFilter {

    private JwtTokenUtil jwt;

    @Autowired
    public JwtBasicAutheticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwt) {
        super(authenticationManager);
        this.jwt = jwt;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);


        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader.isEmpty() || !authorizationHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        String authorizationToken = authorizationHeader.replace("Bearer ", "");

        String username=jwt.getJwtSubject(authorizationToken);

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities= Collections.singleton(new SimpleGrantedAuthority(jwt.getAuthority(authorizationToken)));


        Authentication authentication=new UsernamePasswordAuthenticationToken(
                username,
                null,
                simpleGrantedAuthorities
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }


}
