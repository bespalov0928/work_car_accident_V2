package ru.work.work_car_accident_V2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.work.work_car_accident_V2.Main;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void getLogin() throws Exception {
        this.mockMvc.perform(get("/auth/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

    }

    @Test
    @WithMockUser
    void getSuccess() throws Exception {
        this.mockMvc.perform(get("/auth/success"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("success"));
    }
}