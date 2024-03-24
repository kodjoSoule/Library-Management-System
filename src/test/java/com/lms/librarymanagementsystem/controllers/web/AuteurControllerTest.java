package com.lms.librarymanagementsystem.controllers.web;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.service.AuteurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuteurControllerTest {

    @InjectMocks
    AuteurController auteurController;

    @Mock
    AuteurService auteurService;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void indexTest() {
        // Arrange
        Auteur auteur1 = new Auteur();
        Auteur auteur2 = new Auteur();
        when(auteurService.getAllAuteurs()).thenReturn(Arrays.asList(auteur1, auteur2));

        // Act
        String viewName = auteurController.index(model, null, null);

        // Assert
        assertEquals("/admin/auteurs-manager", viewName);
        verify(auteurService, times(1)).getAllAuteurs();
    }

    @Test
    public void addTest() {
        // Act
        String viewName = auteurController.add(model);

        // Assert
        assertEquals("/admin/auteur-form-new", viewName);
    }

    @Test
    public void addAuteurTest() {
        // Arrange
        Auteur auteur = new Auteur();
        when(auteurService.saveAuteur(auteur)).thenReturn(auteur);

        // Act
        String viewName = auteurController.addAuteur(auteur);

        // Assert
        assertEquals("redirect:/admin/auteurs-manager", viewName);
        verify(auteurService, times(1)).saveAuteur(auteur);
    }

    @Test
    public void deleteTest() {
        // Arrange
        Long id = 1L;

        // Act
        String viewName = auteurController.delete(id);

        // Assert
        assertEquals("redirect:/admin/auteurs-manager", viewName);
        verify(auteurService, times(1)).deleteAuteur(id);
    }

    @Test
    public void confirmDeleteTest() {
        // Arrange
        int id = 1;
        Auteur auteur = new Auteur();
        when(auteurService.getAuteurById(id)).thenReturn(auteur);
        // Act
        String viewName = auteurController.confirmDelete(id, model);
        // Assert
        assertEquals("admin/auteur-confirm-delete", viewName);
        verify(auteurService, times(1)).getAuteurById(id);
    }
}