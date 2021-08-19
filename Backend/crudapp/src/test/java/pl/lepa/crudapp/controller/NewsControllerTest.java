package pl.lepa.crudapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import pl.lepa.crudapp.model.dto.NewsDTO;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin")
class NewsControllerTest {


    final String URL = "/api/v1/news";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldGetOneNews() throws Exception {


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URL + "/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    void shouldGetSomeNews() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .param("page", "1")
                .param("size", "10"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    void shouldEditNews() throws Exception {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setArticle("Edited Article");
        newsDTO.setTitle("Edited Title");

        String json = objectMapper.writeValueAsString(newsDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(URL + "/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    void shouldDeleteNews() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    void shouldCreateNews() throws Exception {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setArticle("Edited Article");
        newsDTO.setTitle("Edited Title");

        String json = objectMapper.writeValueAsString(newsDTO);
        InputStream inputStream = getClass().getResourceAsStream("/test/testImage.png");

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file", "testimage.png", MediaType.IMAGE_PNG_VALUE, inputStream
        );

        MockMultipartFile fileJson= new MockMultipartFile("news","json","application/json",json.getBytes(StandardCharsets.UTF_8));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.multipart(URL)
                .file(mockMultipartFile)
                .file(fileJson))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }
}
