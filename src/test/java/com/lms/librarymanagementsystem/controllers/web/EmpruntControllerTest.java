package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.Emprunt;
import com.lms.librarymanagementsystem.service.EmpruntService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmpruntControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpruntService empruntService;

    private Emprunt emprunt1;
    private Emprunt emprunt2;

    @BeforeEach
    void setUp() {
        emprunt1 = new Emprunt();
        emprunt1.setId(1L);

        emprunt2 = new Emprunt();
        emprunt2.setId(2L);
    }

    @Test
    void getAllEmprunts() throws Exception {
        when(empruntService.getAllEmprunts()).thenReturn(Arrays.asList(emprunt1, emprunt2));

        mockMvc.perform(get("/admin/emprunts/admin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(emprunt1.getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(emprunt2.getId().intValue())));
    }

    @Test
    void getEmpruntById() throws Exception {
        when(empruntService.getEmpruntById(1L)).thenReturn(emprunt1);

        mockMvc.perform(get("/admin/emprunt/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(emprunt1.getId().intValue())));
    }

    @Test
    void creerEmprunt() throws Exception {

        empruntService.saveEmprunt(emprunt1);
        mockMvc.perform(get("/admin/emprunt/creer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(emprunt1.getId().intValue())));
    }
}