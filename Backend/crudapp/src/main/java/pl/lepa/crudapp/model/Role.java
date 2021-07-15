package pl.lepa.crudapp.model;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN("ADMIN"),
    MODERATION("MODERATION"),
    USER("USER");

    private String currentRole;

    Role(String role) {
        this.currentRole = role;
    }

    @Override
    public String getAuthority() {
        return "ROLE_"+this.currentRole;
    }
}
