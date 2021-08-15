package pl.lepa.crudapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.lepa.crudapp.model.News;
import pl.lepa.crudapp.model.dto.NewsDto;
import pl.lepa.crudapp.model.dto.NewsResponseDTO;
import pl.lepa.crudapp.service.NewsService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "News")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @GetMapping("/news/{id}")
    public NewsResponseDTO getOneNews(@PathVariable("id") long id) {
        return newsService.getNews(id);
    }

    @GetMapping("/news")
    public List<NewsResponseDTO> getSomeNews(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return newsService.newsList(page, size);
    }

    @PutMapping("/news/{id}")
    public ResponseEntity<String> editNews(@PathVariable("id") long id, @RequestBody NewsDto newsDto) {
        newsService.editNews(id, newsDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable("id") long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/news", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createNews(@RequestPart("news") String newsDto,
                                             @RequestPart("file") Set<MultipartFile> files) {

        newsService.createNews(newsDto, files);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
