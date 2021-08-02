package pl.lepa.crudapp.service;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.lepa.crudapp.dao.CommentRepository;
import pl.lepa.crudapp.dao.NewsRepository;
import pl.lepa.crudapp.exceptions.CommentNotFound;
import pl.lepa.crudapp.exceptions.InvalidUser;
import pl.lepa.crudapp.exceptions.NewsNotFound;
import pl.lepa.crudapp.model.*;
import pl.lepa.crudapp.model.dto.NewsDto;
import pl.lepa.crudapp.model.user.Role;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NewsService {

    private final UploadService uploadService;
    private final NewsRepository newsRepository;
    private final UserService userService;
    private final CommentRepository commentRepository;

    public NewsService(UploadService uploadService, NewsRepository newsRepository, UserService userService, CommentRepository commentRepository) {
        this.uploadService = uploadService;
        this.newsRepository = newsRepository;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }


    public void createNews(NewsDto newsDto, Set<MultipartFile> files) {
        News news = new News();

        news.setTitle(news.getTitle());
        news.setArticle(newsDto.getArticle());
        news.setAuthor(userService.currentUser());

        news.setCreateDate(LocalDateTime.now());
        news.setLastEdit(LocalDateTime.now());

        Set<Image> imageSet = new HashSet<>();
        Image image = new Image();

        for (MultipartFile file : files) {
            image.setAlt(file.getOriginalFilename());
            image.setLink(uploadService.uploadLocalFile(file));
            imageSet.add(image);
        }
        news.setImageSet(imageSet);
        newsRepository.save(news);
    }

    public void editNews(long id, NewsDto newsDto) {
        News news = newsRepository.findById(id).orElseThrow(NewsNotFound::new);
        if (userService.currentUser().getRole().equals(Role.ADMIN) || !userService.currentUser().getUsername().equals(news.getAuthor().getUsername())) {
            throw new InvalidUser("Access denied");
        }
        news.setTitle(newsDto.getTitle());
        news.setLastEdit(LocalDateTime.now());
        news.setArticle(newsDto.getArticle());

        newsRepository.save(news);
    }

    public List<News> newsList(int page, int size) {

        return newsRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "Id"))).toList();
    }

    public News getNews(long id) {
        return newsRepository.findById(id).orElseThrow(NewsNotFound::new);
    }

    public void deleteNews(long id) {
        newsRepository.deleteById(id);
    }

    public void createComment(long idNews, CommentDto commentDto) {
        News news = newsRepository.findById(idNews).orElseThrow(NewsNotFound::new);

        Comment comment = new Comment();

        comment.setCommentText(commentDto.getCommentText());
        comment.setAuthor(userService.currentUser());
        comment.setCreateDate(LocalDateTime.now());
        comment.setLastEdit(LocalDateTime.now());
        comment.setNews(news);

        commentRepository.save(comment);
    }

    public void editComment(long idComment, CommentDto commentDto) {
        Comment comment = commentRepository.findById(idComment).orElseThrow(CommentNotFound::new);
        comment.setCommentText(commentDto.getCommentText());
        comment.setLastEdit(LocalDateTime.now());

        commentRepository.save(comment);

    }

    public List<Comment> commentList(long idNews, int page, int size) {
        News news = newsRepository.findById(idNews).orElseThrow(NewsNotFound::new);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "Id"));
        return commentRepository.findAllByNews(news, pageable).toList();
    }

}
