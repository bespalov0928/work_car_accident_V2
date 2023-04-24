package ru.work.work_car_accident_V2.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.work.work_car_accident_V2.Main;
import ru.work.work_car_accident_V2.model.User;
import ru.work.work_car_accident_V2.service.SimpleUserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class RegControllerTest {

    private final User user = new User("userTest", "userTest");

    @MockBean
    SimpleUserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void regPage() throws Exception {
//        this.mockMvc.perform(get("/reg"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("/reg"));
    }

    @Test
    @WithMockUser
    public void regSave() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", user.getUsername())
                        .param("password", user.getPassword()))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userService).save(argument.capture());
        assertThat(argument.getValue().getUsername()).isEqualTo(user.getUsername());
    }
}