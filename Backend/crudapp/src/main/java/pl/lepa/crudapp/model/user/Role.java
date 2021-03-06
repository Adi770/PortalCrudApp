package pl.lepa.crudapp.model.user;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN("ADMIN"),
    MODERATION("MODERATION"),
    USER("USER");

    private final String currentRole;

    Role(String role) {
        this.currentRole = role;
    }

    @Override
    public String getAuthority() {
        return "ROLE_"+this.currentRole;
    }
}
