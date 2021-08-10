package pl.lepa.crudapp.model.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {

    private Long id;
    private AuthorDTO author;
    private String commentText;
    private LocalDateTime createDate;
    private LocalDateTime lastEdit;

}
