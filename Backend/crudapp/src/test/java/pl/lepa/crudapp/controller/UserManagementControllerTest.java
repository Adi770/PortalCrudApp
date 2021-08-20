package pl.lepa.crudapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import pl.lepa.crudapp.model.dto.UserAdminDTO;
import pl.lepa.crudapp.model.dto.UserDTO;
import pl.lepa.crudapp.model.dto.UserUpdateDTO;
import pl.lepa.crudapp.model.user.RecoveryMessage;
import pl.lepa.crudapp.model.user.Role;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@WithMockUser
class UserManagementControllerTest {

    private final String URL = "/api/v1/AccountManagement";

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;


    @Test
    void shouldRegisterNewUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("new User");
        userDTO.setPassword("testPassword");
        userDTO.setEmail("testEmail");
        String json = objectMapper.writeValueAsString(userDTO);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void shouldLoginUser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/login"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void shouldRegisterNewUserByAdmin() throws Exception {
        UserAdminDTO userAdminDTO = new UserAdminDTO();
        userAdminDTO.setUsername("new User");
        userAdminDTO.setPassword("testPassword");
        userAdminDTO.setEmail("testEmail");
        userAdminDTO.setRole(Role.MODERATION);
        String json = objectMapper.writeValueAsString(userAdminDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/admin/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void shouldGetAllRole() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/roles"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void shouldCurrentRole() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + "/role"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    void shouldResetPassword() throws Exception {
        RecoveryMessage recoveryMessage = new RecoveryMessage();
        recoveryMessage.setMessage("test message");
        recoveryMessage.setTitle("test tittle");
        recoveryMessage.setUserEmail("testEmail@gmail.com");
        recoveryMessage.setBaseUrl("http://test.com/");

        String json = objectMapper.writeValueAsString(recoveryMessage);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL + "/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void shouldChangePassword() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL + "/password")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
//                .andReturn();
    }

    @Test
    void shouldUpdateUserData() throws Exception {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setEmail("test@gmail.com");
        String json = objectMapper.writeValueAsString(userUpdateDTO);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL + "/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void shouldUpdateByAdmin() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("new User");
        userDTO.setPassword("testPassword");
        userDTO.setEmail("testEmail");
        userDTO.setId(1L);
        String json = objectMapper.writeValueAsString(userDTO);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL + "/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }
}
