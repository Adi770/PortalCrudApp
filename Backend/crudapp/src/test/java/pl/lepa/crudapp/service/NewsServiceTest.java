package pl.lepa.crudapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import pl.lepa.crudapp.dao.CommentRepository;
import pl.lepa.crudapp.dao.ImageRepository;
import pl.lepa.crudapp.dao.NewsRepository;
import pl.lepa.crudapp.model.Image;
import pl.lepa.crudapp.model.dto.NewsDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class NewsServiceTest {

    @InjectMocks
    private NewsService newsService;

    @Mock
    CommentRepository commentRepository;
    @Mock
    NewsRepository newsRepository;
    @Mock
    ImageRepository imageRepository;


    @Test
    void shouldCreateNews() throws IOException {
        //given

        NewsDto newsDto = new NewsDto();
        newsDto.setTitle("TEST TITLE");
        newsDto.setArticle("TEST ARTICLE");

        InputStream inputStream = getClass().getResourceAsStream("/test/imagetest.png");

        Set<MockMultipartFile> images = new HashSet<>();
        images.add(new MockMultipartFile("image", "", "image/png", inputStream));

        //when


        //then
    }

    @Test
    void shouldEditNews() {
    }

    @Test
    void shouldNewsList() {
    }

    @Test
    void shouldGetNews() {
    }

    @Test
    void shouldDeleteNews() {
    }

    @Test
    void shouldCreateComment() {
    }

    @Test
    void shouldEditComment() {
    }

    @Test
    void shouldCommentList() {
    }

    @Test
    void getComment() {
    }
}
