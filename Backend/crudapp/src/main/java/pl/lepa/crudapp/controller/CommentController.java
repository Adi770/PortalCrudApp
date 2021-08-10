package pl.lepa.crudapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lepa.crudapp.model.dto.CommentDto;
import pl.lepa.crudapp.model.dto.CommentResponseDTO;
import pl.lepa.crudapp.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private final NewsService newsService;

    public CommentController(NewsService newsService) {
        this.newsService = newsService;
    }


    @GetMapping("news/{idNews}/comment")
    public List<CommentResponseDTO> getSomeComments(@PathVariable long idNews,
                                                    @RequestParam(name = "page") int page,
                                                    @RequestParam(name = "size") int size) {
        return newsService.commentList(idNews, page, size);
    }

    @GetMapping("news/{idNews}/comment/{idCom}")
    public CommentResponseDTO getOneComment(@PathVariable(name = "idCom") long idCom,
                                            @PathVariable(name = "idNews") String idNews) {
        return newsService.getComment(idCom);
    }

    @PutMapping("news/{idNews}/comment/{idCom}")
    public ResponseEntity<String> editComment(@PathVariable(name = "idCom") long id,
                                              @RequestBody CommentDto comDto,
                                              @PathVariable(name = "idNews") String idNews) {

        newsService.editComment(id, comDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("news/{idNews}/comment")
    public ResponseEntity<String> createComment(@PathVariable(name = "idNews") long id,
                                              @RequestBody CommentDto comDto) {

        newsService.createComment(id, comDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
