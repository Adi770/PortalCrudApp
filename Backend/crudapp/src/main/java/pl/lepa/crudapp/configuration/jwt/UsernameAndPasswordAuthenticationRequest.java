package pl.lepa.crudapp.configuration.jwt;


import lombok.Data;

@Data
public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;
}
