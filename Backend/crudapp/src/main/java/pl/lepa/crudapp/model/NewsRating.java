package pl.lepa.crudapp.model;

import lombok.Data;
import pl.lepa.crudapp.model.user.User;

import javax.persistence.*;

@Data
@Entity
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

    private int rate;
}
