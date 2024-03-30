package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.repository.AuteurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuteurServiceTest {

    @InjectMocks
    AuteurService auteurService;

    @Mock
    AuteurRepository auteurRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAuteurs() {
        // Given
        Auteur auteur1 = new Auteur();
        Auteur auteur2 = new Auteur();
        List<Auteur> expectedAuteurs = Arrays.asList(auteur1, auteur2);
        when(auteurRepository.findAll()).thenReturn(expectedAuteurs);

        // When
        List<Auteur> actualAuteurs = auteurService.getAllAuteurs();

        // Then
        assertEquals(expectedAuteurs, actualAuteurs);
    }

    @Test
    void saveAuteur() {
        // Given
        Auteur auteur = new Auteur();
        when(auteurRepository.save(auteur)).thenReturn(auteur);

        // When
        Auteur savedAuteur = auteurService.saveAuteur(auteur);

        // Then
        assertEquals(auteur, savedAuteur);
    }

    @Test
    void getAuteurById() {
        // Given
        Auteur auteur = new Auteur();

        when(auteurRepository.findById(1)).
                thenReturn(auteur);

        // When
        Auteur actualAuteur = auteurService.getAuteurById(1);

        // Then
        assertEquals(auteur, actualAuteur);
    }

    @Test
    void addAuteur() {
        // Given
        Auteur auteur = new Auteur();
        when(auteurRepository.save(auteur)).thenReturn(auteur);

        // When
        Auteur addedAuteur = auteurService.addAuteur(auteur);

        // Then
        assertEquals(auteur, addedAuteur);
    }

    @Test
    void updateAuteur() {
        // Given
        Auteur auteur = new Auteur();
        when(auteurRepository.findById(1)).thenReturn(auteur);
        when(auteurRepository.save(auteur)).thenReturn(auteur);

        // When
        Auteur updatedAuteur = auteurService.updateAuteur(1, auteur);

        // Then
        assertEquals(auteur, updatedAuteur);
    }

    @Test
    void deleteAuteur() {
        // Given
        Auteur auteur = new Auteur();
        when(auteurRepository.findById(1L)).thenReturn(Optional.of(auteur));
        doNothing().when(auteurRepository).deleteById(1L);

        // When
        auteurService.deleteAuteur(1L);

        // Then
        verify(auteurRepository, times(1)).deleteById(1L);
    }

    @Test
    void findPaginated() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Auteur> page = mock(Page.class);
        when(auteurRepository.findAll(pageable)).thenReturn(page);

        // When
        Page<Auteur> result = auteurService.findPaginated(pageable);

        // Then
        assertEquals(page, result);
    }


}