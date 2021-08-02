package pl.lepa.crudapp.model.user;

import lombok.Data;
import pl.lepa.crudapp.model.NewsRating;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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
    private boolean isAccountNonExpired;

    @Column(name="ACCOUNT_NON_LOCKED")
    private boolean isAccountNonLocked;

    @Column(name="CREDENTIALS_NON_EXPIRED")
    private boolean isCredentialsNonExpired;

    @Column(name="ENABLED")
    private boolean isEnabled;

    @OneToMany(mappedBy = "user")
    private Set<NewsRating> newsRating;
}
