package pl.lepa.crudapp.model;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import pl.lepa.crudapp.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "NEWS")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEWS_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ARTICLE")
    private String article;


    @ManyToOne
    @JoinColumn(name = "AUTHOR_FK")
    private User author;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "LAST_EDIT")
    private LocalDateTime lastEdit;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private Set<Comment> commentSet;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Image> imageSet;

    @OneToMany(mappedBy = "news")
    private Set<NewsRating> newsRatings;

    public Double getNewsRatings() {
        return newsRatings.stream().mapToDouble(NewsRating::getRate).average().orElse(Double.NaN);
    }

    public void setNewsRatings(Set<NewsRating> newsRatings) {
        this.newsRatings = newsRatings;
    }
}
