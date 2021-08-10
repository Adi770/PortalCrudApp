package pl.lepa.crudapp.model.dto;


import lombok.Data;
import pl.lepa.crudapp.model.Comment;
import pl.lepa.crudapp.model.Image;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class NewsResponseDTO {

        private Long id;

        private String title;

        private String article;

        private AuthorDTO author;

        private LocalDateTime createDate;

        private LocalDateTime lastEdit;

        private Set<CommentResponseDTO> commentSet;

        private Set<Image> imageSet;

        private Double newsRatings;
}
