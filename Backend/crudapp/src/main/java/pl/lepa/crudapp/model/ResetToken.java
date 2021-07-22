package pl.lepa.crudapp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "RESET_TOKEN")
public class ResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOKEN_ID")
    private Long id;

    @Column(name = "TOKEN")
    private String token;

    @OneToOne
    @JoinColumn(name = "USER_APPLICATION")
    private User user;

    @Column(name = "TIME_TO_EXPIRED")
    private LocalDateTime timeToExpired;


}
