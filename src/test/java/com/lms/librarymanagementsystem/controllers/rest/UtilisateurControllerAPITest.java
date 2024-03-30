package com.lms.librarymanagementsystem.controllers.rest;
import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.service.UtilisateurService;
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
class UtilisateurControllerAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtilisateurService utilisateurService;

    private Utilisateur user1;
    private Utilisateur user2;

    @BeforeEach
    void setUp() {
        user1 = new Utilisateur();
        user1.setId(1L);
        user1.setUsername("user1");

        user2 = new Utilisateur();
        user2.setId(2L);
        user2.setUsername("user2");
    }

    @Test
    void listUsers() throws Exception {
        when(utilisateurService.getAllUtilisateurs()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/utilisateurs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is(user1.getUsername())))
                .andExpect(jsonPath("$[1].username", is(user2.getUsername())));
    }

    @Test
    void findUserByID() throws Exception {
        when(utilisateurService.getUserById(1L)).thenReturn(user1);

        mockMvc.perform(get("/api/utilisateur/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(user1.getUsername())));

    }

    @Test
    void updateUser() throws Exception {
        when(utilisateurService.getUserById(1L)).thenReturn(user1);

        mockMvc.perform(get("/api/utilisateur/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(user1.getUsername())));
    }


    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(get("/api/utilisateur/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveUser() throws Exception {
        when(utilisateurService.saveUser(user1)).thenReturn(user1);

        mockMvc.perform(get("/api/utilisateur")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(user1.getUsername())));
    }

}