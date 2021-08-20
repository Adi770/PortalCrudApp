package pl.lepa.crudapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import pl.lepa.crudapp.model.dto.CommentDTO;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@WithMockUser(username = "admin")
class CommentControllerTest {

    private final String URL_NEWS = "/api/v1/news";
    private final String URL_COMMENTS = "/api/v1/comments";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }


    @Test
    void shouldCreateComment() throws Exception {
        String idNews = "/1";
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentText("TEST COMMENT");
        String json = objectMapper.writeValueAsString(commentDTO);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL_NEWS + idNews + "/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }


    @Test
    void shouldGetSomeComments() throws Exception {
        String idNews = "/1";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL_NEWS + idNews + "/comments")
                .param("page", "1")
                .param("size", "10"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    void shouldGetOneComment() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL_COMMENTS + "/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();


    }

    @Test
    void shouldEditComment() throws Exception {
        String idComments = "/1";
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setCommentText("EDITED TEXT");
        String json=objectMapper.writeValueAsString(commentDTO);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL_COMMENTS + idComments)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }


}
