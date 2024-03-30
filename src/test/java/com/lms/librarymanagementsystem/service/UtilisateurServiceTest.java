package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.repository.UtilisateurRepository;
import com.lms.librarymanagementsystem.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UtilisateurServiceTest {

    @InjectMocks
    UtilisateurService utilisateurService;

    @Mock
    UtilisateurRepository utilisateurRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        // Given
        Utilisateur user1 = new Utilisateur();
        Utilisateur user2 = new Utilisateur();
        List<Utilisateur> expectedUsers = Arrays.asList(user1, user2);
        when(utilisateurRepository.findAll()).thenReturn(expectedUsers);

        // When
        List<Utilisateur> actualUsers = utilisateurService.getAllUsers();

        // Then
        assertEquals(expectedUsers, actualUsers);
    }
    //get user by id
    @Test
    void getUserById() {
        // Given
        Utilisateur user = new Utilisateur();
        when(utilisateurRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        // When
        Utilisateur actualUser = utilisateurService.getUserById(1L);

        // Then
        assertEquals(user, actualUser);
    }
    //save user
    @Test
    void saveUser() {
        // Given
        Utilisateur user = new Utilisateur();
        when(utilisateurRepository.save(user)).thenReturn(user);

        // When
        Utilisateur actualUser = utilisateurService.saveUser(user);

        // Then
        assertEquals(user, actualUser);
    }
    //delete user
    @Test
    void deleteUser() {
        // Given
        Utilisateur user = new Utilisateur();
        when(utilisateurRepository.findById(1L)).thenReturn(java.util.Optional.of(user));


        utilisateurService.deleteUser(1L);

    }

}