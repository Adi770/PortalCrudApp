package pl.lepa.crudapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import pl.lepa.crudapp.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_FK")
    private User author;

    @Column(name = "COMMENT_TEXT")
    private String commentText;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "LAST_EDIT")
    private LocalDateTime lastEdit;

    @ManyToOne
    @JsonIgnore
    private News news;


}
