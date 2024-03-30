package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.service.LivreService;
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
class LivreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivreService livreService;

    private Livre livre1;
    private Livre livre2;

    @BeforeEach
    void setUp() {
        livre1 = new Livre();
        livre1.setId(1L);

        livre2 = new Livre();
        livre2.setId(2L);
    }

    @Test
    void getAllLivres() throws Exception {
        when(livreService.getAllLivres()).thenReturn(Arrays.asList(livre1, livre2));

        mockMvc.perform(get("/admin/livres")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(livre1.getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(livre2.getId().intValue())));
    }

    @Test
    void getLivreById() throws Exception {
        when(livreService.getLivreById(1L)).thenReturn(livre1);

        mockMvc.perform(get("/admin/livre/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(livre1.getId().intValue())));
    }


}