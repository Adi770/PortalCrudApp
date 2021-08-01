package pl.lepa.crudapp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEWS_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ARTICLE")
    private String article;

    @Column(name = "AUTHOR")
    private User author;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "LAST_EDIT")
    private LocalDateTime lastEdit;

    @Column(name = "COMMENTS")
    private Set<Comment> commentSet;


}
