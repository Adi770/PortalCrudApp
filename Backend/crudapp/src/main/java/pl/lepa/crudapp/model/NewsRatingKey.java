package pl.lepa.crudapp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class NewsRatingKey implements Serializable {

    @Column(name = "NEWS_ID")
    private Long newsId;

    @Column(name = "USER_ID")
    private Long userId;

}
