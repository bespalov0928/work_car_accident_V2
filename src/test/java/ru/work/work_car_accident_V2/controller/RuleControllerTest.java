package ru.work.work_car_accident_V2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.work.work_car_accident_V2.Main;
import ru.work.work_car_accident_V2.model.Rule;
import ru.work.work_car_accident_V2.service.SimpleRuleService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class RuleControllerTest {

    private final Rule rule = new Rule(1, "rule1");

    @MockBean
    private SimpleRuleService simpleRuleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void formIndexRule() throws Exception {
        this.mockMvc.perform(get("/rule/formIndexRule"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("rule/indexRule"));
    }

    @Test
    @WithMockUser
    void formCreateRule() throws Exception {
        this.mockMvc.perform(get("/rule/formCreateRule"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("rule/createRule"));
    }

    @Test
    @WithMockUser
    void formUpdateRule() throws Exception {
        simpleRuleService.save(rule);
        when(simpleRuleService.findById(1)).thenReturn(Optional.of(rule));
        this.mockMvc.perform(get("/rule/formUpdateRule?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("rule/updateRule"));
    }
}