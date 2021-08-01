package pl.lepa.crudapp.model.dto;

import lombok.Data;
import pl.lepa.crudapp.model.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;

}
