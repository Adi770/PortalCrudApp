package pl.lepa.crudapp.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private Long id;

    @Column(name = "ALT")
    private String alt;

    @Column(name = "LINK")
    private String link;


}
