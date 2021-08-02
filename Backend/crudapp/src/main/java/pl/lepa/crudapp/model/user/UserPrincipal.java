package pl.lepa.crudapp.model.user;


import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.lepa.crudapp.model.user.User;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
public class UserPrincipal implements UserDetails {

    private User userApplication;

    public UserPrincipal(User userApplication) {
        this.userApplication = userApplication;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userApplication.getRole().getAuthority()));
    }

    @Override
    public String getPassword() {
        return userApplication.getPassword();
    }

    @Override
    public String getUsername() {
        return userApplication.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
