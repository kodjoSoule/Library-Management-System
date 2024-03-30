package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.service.AuteurService;
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
class AuteurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuteurService auteurService;

    private Auteur auteur1;
    private Auteur auteur2;

    @BeforeEach
    void setUp() {
        auteur1 = new Auteur();
        auteur1.setId(1L);

        auteur2 = new Auteur();
        auteur2.setId(2L);
    }

    @Test
    void getAllAuteurs() throws Exception {
        when(auteurService.getAllAuteurs()).thenReturn(Arrays.asList(auteur1, auteur2));

        mockMvc.perform(get("/admin/auteurs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(auteur1.getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(auteur2.getId().intValue())));
    }

    @Test
    void getAuteurById() throws Exception {
        when(auteurService.getAuteurById(1)).thenReturn(auteur1);

        mockMvc.perform(get("/admin/auteurs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(auteur1.getId().intValue())));
    }

}