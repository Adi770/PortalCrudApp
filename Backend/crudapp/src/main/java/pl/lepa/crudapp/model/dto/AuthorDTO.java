package pl.lepa.crudapp.model.dto;

import lombok.Data;
import pl.lepa.crudapp.model.user.Role;

@Data
public class AuthorDTO {
    private String username;
    private Role role;
}
