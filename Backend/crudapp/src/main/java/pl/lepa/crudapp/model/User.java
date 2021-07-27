package pl.lepa.crudapp.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "USER_APPLICATION")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name="USERNAME")
    private String username;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="EMAIL")
    private String email;
    @Column(name="USER_ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name="CREATE_DATE")
    private Date createDate;
    @Column(name="ACCOUNT_NON_EXPIRED")
    public boolean isAccountNonExpired;
    @Column(name="ACCOUNT_NON_LOCKED")
    public boolean isAccountNonLocked;
    @Column(name="CREDENTIALS_NON_EXPIRED")
    public boolean isCredentialsNonExpired;
    @Column(name="ENABLED")
    public boolean isEnabled;
}
