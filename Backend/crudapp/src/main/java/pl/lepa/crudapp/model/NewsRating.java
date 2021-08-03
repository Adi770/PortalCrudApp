package pl.lepa.crudapp.model;

import lombok.Data;
import pl.lepa.crudapp.model.user.User;

import javax.persistence.*;

@Data
@Entity(name = "NEWS_RATING")
public class NewsRating {

    @EmbeddedId
    private NewsRatingKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @MapsId("newsId")
    @JoinColumn(name = "NEWS_ID")
    private News news;

    @Column(name = "RATE")
    private Integer rate;
}
