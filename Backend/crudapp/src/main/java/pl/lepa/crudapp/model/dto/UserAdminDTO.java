package pl.lepa.crudapp.model.dto;

import lombok.Data;
import pl.lepa.crudapp.model.user.Role;

import java.util.Date;

@Data
public class UserAdminDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private Date createDate;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
}
