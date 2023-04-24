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
import ru.work.work_car_accident_V2.model.Photo;
import ru.work.work_car_accident_V2.model.Rule;
import ru.work.work_car_accident_V2.service.SimpleAccidentService;

import java.util.ArrayList;
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
class AccControllerTest {

    private final Accident accident = new Accident(1, "name", "text", "adress", new AccidentType(), new ArrayList<Rule>(), new ArrayList<Photo>());

    @MockBean
    private SimpleAccidentService accidentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void formCreateAcc() throws Exception {
        this.mockMvc.perform(get("/acc/formCreateAcc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/acc/createAcc"));
    }

    @Test
    @WithMockUser
    void formUpdateAcc() throws Exception {
        var idRule = new String[]{"1"};
        accidentService.save(accident, idRule);
        when(accidentService.findById(accident.getId())).thenReturn(Optional.of(accident));
        this.mockMvc.perform(get("/acc/formUpdateAcc?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/acc/updateAcc"));
    }

    @WithMockUser
    @Test
    public void save() throws Exception {
        String[] idR = new String[]{"1"};

        this.mockMvc.perform(post("/acc/save")
                        .param("name", accident.getName())
                        .param("text", accident.getText())
                        .param("address", accident.getAddress())
                        .param("idRule", idR))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).save(argument.capture(), eq(idR));
        assertThat(argument.getValue().getName()).isEqualTo(accident.getName());
    }

    @WithMockUser
    @Test
    public void update() throws Exception {
        String[] idRule = new String[]{"1"};
        accidentService.save(accident, idRule);

        this.mockMvc.perform(post("/acc/update")
                        .param("name", "name1")
                        .param("text", accident.getText())
                        .param("address", accident.getAddress())
                        .param("idRule", idRule))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).update(argument.capture(), eq(idRule));
        assertThat(argument.getValue().getName()).isEqualTo("name1");
    }
}