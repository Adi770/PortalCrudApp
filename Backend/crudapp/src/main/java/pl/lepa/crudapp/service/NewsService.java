package pl.lepa.crudapp.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import pl.lepa.crudapp.model.Comment;
import pl.lepa.crudapp.model.Image;
import pl.lepa.crudapp.model.News;
import pl.lepa.crudapp.model.dto.CommentDTO;
import pl.lepa.crudapp.model.dto.CommentResponseDTO;
import pl.lepa.crudapp.model.dto.NewsDTO;
import pl.lepa.crudapp.model.dto.NewsResponseDTO;
import pl.lepa.crudapp.model.user.Role;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NewsService {

    private final UploadService uploadService;
    private final NewsRepository newsRepository;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public NewsService(UploadService uploadService,
                       NewsRepository newsRepository,
                       UserService userService,
                       CommentRepository commentRepository,
                       ObjectMapper objectMapper,
                       ModelMapper modelMapper) {
        this.uploadService = uploadService;
        this.newsRepository = newsRepository;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
    }


    public News createNews(String newsStringDto, Set<MultipartFile> files) {

        NewsDTO newsDto = new NewsDTO();
        try {
            newsDto = objectMapper.readValue(newsStringDto, NewsDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        News news = new News();

        news.setTitle(newsDto.getTitle());
        news.setArticle(newsDto.getArticle());
        news.setAuthor(userService.currentUser());

        news.setCreateDate(LocalDateTime.now());
        news.setLastEdit(LocalDateTime.now());

        Set<Image> imageSet = new HashSet<>();


        for (MultipartFile file : files) {
            Image image = new Image();
            image.setAlt(file.getOriginalFilename());
            image.setLink(uploadService.uploadLocalFile(file));
            imageSet.add(image);
        }

        log.info("Image Set");
        log.info(imageSet.toString());
        news.setImageSet(imageSet);
        return newsRepository.save(news);
    }

    public void editNews(long id, NewsDTO newsDto) {
        News news = newsRepository.findById(id).orElseThrow(NewsNotFound::new);
        if (!userService.currentUser().getRole().equals(Role.ADMIN) || !userService.currentUser().getUsername().equals(news.getAuthor().getUsername())) {
            throw new InvalidUser("Access denied");
        }
        news.setTitle(newsDto.getTitle());
        news.setLastEdit(LocalDateTime.now());
        news.setArticle(newsDto.getArticle());

        newsRepository.save(news);
    }

    public List<NewsResponseDTO> newsList(int page, int size) {
        List<News> newsList = newsRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "Id"))).toList();
        List<NewsResponseDTO> newsResponseDTOList = newsList.stream().map(news -> modelMapper.map(news, NewsResponseDTO.class)).collect(Collectors.toList());
        return newsResponseDTOList;
    }

    public NewsResponseDTO getNews(long id) {
        News news = newsRepository.findById(id).orElseThrow(NewsNotFound::new);
        return modelMapper.map(news, NewsResponseDTO.class);
    }

    public void deleteNews(long id) {
        newsRepository.deleteById(id);
    }

    public void createComment(long idNews, CommentDTO commentDto) {
        News news = newsRepository.findById(idNews).orElseThrow(NewsNotFound::new);

        Comment comment = new Comment();

        comment.setCommentText(commentDto.getCommentText());
        comment.setAuthor(userService.currentUser());
        comment.setCreateDate(LocalDateTime.now());
        comment.setLastEdit(LocalDateTime.now());
        comment.setNews(news);

        commentRepository.save(comment);
    }

    public void editComment(long idComment, CommentDTO commentDto) {
        Comment comment = commentRepository.findById(idComment).orElseThrow(CommentNotFound::new);
        comment.setCommentText(commentDto.getCommentText());
        comment.setLastEdit(LocalDateTime.now());

        commentRepository.save(comment);

    }

    public List<CommentResponseDTO> commentList(long idNews, int page, int size) {
        News news = newsRepository.findById(idNews).orElseThrow(NewsNotFound::new);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "Id"));
        List<Comment> commentList = commentRepository.findAllByNews_Id(idNews, pageable).toList();
        List<CommentResponseDTO> commentResponseDTOList = commentList
                .stream().map(comment -> modelMapper.map(comment, CommentResponseDTO.class)).collect(Collectors.toList());
        return commentResponseDTOList;
    }

    public CommentResponseDTO getComment(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFound::new);
        return modelMapper.map(comment, CommentResponseDTO.class);
    }

}
