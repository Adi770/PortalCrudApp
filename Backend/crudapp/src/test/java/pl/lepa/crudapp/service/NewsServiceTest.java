package pl.lepa.crudapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.lepa.crudapp.dao.CommentRepository;
import pl.lepa.crudapp.dao.NewsRepository;
import pl.lepa.crudapp.model.News;
import pl.lepa.crudapp.model.dto.NewsDTO;
import pl.lepa.crudapp.model.dto.NewsResponseDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser("ADMIN")
@SpringBootTest
class NewsServiceTest {


    @InjectMocks
    private NewsService newsService;


    @Mock
    private UploadService uploadService;
    @Mock
    private NewsRepository newsRepository;
    @Mock
    private UserService userService;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private ModelMapper modelMapper;


    @Autowired
    ObjectMapper testObjectMapper;

//Inject Mocks doesnt works, configure manually
    @BeforeEach
    void setUp() {
        newsService = new NewsService(uploadService,
                newsRepository,
                userService,
                commentRepository,
                objectMapper,
                modelMapper);
    }

    @Test
    void shouldCreateNews() throws IOException {
        //given
        NewsDTO testNews=new NewsDTO();

        testNews.setTitle("TEST TITLE");
        testNews.setArticle("TEST ARTICLE");

        InputStream inputStream = getClass().getResourceAsStream("/test/testImage.png");

        Set<MultipartFile> images = new HashSet<>();
        images.add(new MockMultipartFile("image", "", "image/png", inputStream));

        Mockito.when(objectMapper.readValue(Mockito.anyString(), Mockito.eq(NewsDTO.class))).thenReturn(testNews);

        String jsonNews = testObjectMapper.writeValueAsString(testNews);
        testObjectMapper.readValue(jsonNews, NewsDTO.class);
        //when
        News news = newsService.createNews(jsonNews, images);

        //then


    }


    @Test
    void shouldEditNews() {
        //given
        NewsDTO newsDto = new NewsDTO();
        newsDto.setTitle("TEST EDIT TITLE");
        newsDto.setArticle("TEST EDIT ARTICLE");


        //when


        //then

    }

    @Test
    void shouldNewsList() {
        //given


        //when

        //then

    }

    @Test
    void shouldGetNews() {

        NewsResponseDTO news = newsService.getNews(1);

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
