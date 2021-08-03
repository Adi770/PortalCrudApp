package pl.lepa.crudapp.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lepa.crudapp.model.News;
import pl.lepa.crudapp.model.dto.NewsDto;
import pl.lepa.crudapp.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "News")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news/{id}")
    public News getOneNews(@PathVariable("id") long id) {
        return newsService.getNews(id);
    }

    @GetMapping("/news")
    public List<News> getSomeNews(@RequestParam int page, @RequestParam int size) {
        return newsService.newsList(page, size);
    }

    @PutMapping("/news/{id}")
    public ResponseEntity<String> editNews(@PathVariable("id")long id, @RequestBody NewsDto newsDto) {
        newsService.editNews(id, newsDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/news/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable("id") long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
