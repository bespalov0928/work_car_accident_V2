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
import ru.work.work_car_accident_V2.model.Accident;
import ru.work.work_car_accident_V2.model.AccidentType;
import ru.work.work_car_accident_V2.service.SimpleTypeService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class TypeControllerTest {

    private final AccidentType accidentType = new AccidentType(1, "type1");

    @MockBean
    SimpleTypeService typeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void formIndexType() throws Exception {
        this.mockMvc.perform(get("/type/formIndexType"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("type/indexType"));
    }

    @Test
    @WithMockUser
    void formCreateType() throws Exception {
        this.mockMvc.perform(get("/type/formCreateType"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("type/createType"));
    }

    @Test
    @WithMockUser
    void formUpdateType() throws Exception {
        typeService.save(accidentType);
        when(typeService.findById(1)).thenReturn(Optional.of(accidentType));
        this.mockMvc.perform(get("/type/formUpdateType?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("type/updateType"));
    }

    @WithMockUser
    @Test
    public void save() throws Exception {
        this.mockMvc.perform(post("/type/createType")
                        .param("name", accidentType.getName()))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<AccidentType> argument = ArgumentCaptor.forClass(AccidentType.class);
        verify(typeService).save(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo(accidentType.getName());
    }

    @WithMockUser
    @Test
    public void update() throws Exception {
        typeService.save(accidentType);

        this.mockMvc.perform(post("/type/updateType")
                        .param("name", "name1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<AccidentType> argument = ArgumentCaptor.forClass(AccidentType.class);
        verify(typeService).update(argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("name1");
    }
}