package com.lms.librarymanagementsystem.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.service.api.LivreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(APILivreController.class)
@AutoConfigureMockMvc
class APILivreControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private LivreService livreService;

        @Test
        void getAllLivres() throws Exception {
            // Given
            Livre livre1 = new Livre();
            livre1.setId(1L);
            livre1.setTitre("Titre 1");
            Livre livre2 = new Livre();
            livre2.setId(2L);
            livre2.setTitre("Titre 2");
            List<Livre> livres = Arrays.asList(livre1, livre2);
            when(livreService.getAllLivres()).thenReturn(livres);

            // When/Then
            mockMvc.perform(get("/api/livres"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].titre").value("Titre 1"))
                    .andExpect(jsonPath("$[1].id").value(2))
                    .andExpect(jsonPath("$[1].titre").value("Titre 2"));
        }

        @Test
        void getLivreById() throws Exception {
            // Given
            Livre livre = new Livre();
            livre.setId(1L);
            livre.setTitre("Titre 1");
            when(livreService.getLivreById(1L)).thenReturn(Optional.of(livre));

            // When/Then
            mockMvc.perform(get("/api/livres/{id}", 1))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.titre").value("Titre 1"));
        }

        @Test
        void saveLivre() throws Exception {
            // Given
            Livre livre = new Livre();
            livre.setTitre("Nouveau Titre");
            when(livreService.saveLivre(livre)).thenReturn(livre);

            // When/Then
            mockMvc.perform(post("/api/livres")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(livre)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.titre").value("Nouveau Titre"));
        }

    @Test
    void deleteLivre() throws Exception {
    }


}
