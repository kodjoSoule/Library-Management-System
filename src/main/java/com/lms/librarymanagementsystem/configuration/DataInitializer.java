package com.lms.librarymanagementsystem.configuration;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.model.DBUser;
import com.lms.librarymanagementsystem.model.Exemplaire;
import com.lms.librarymanagementsystem.model.Livre;
import com.lms.librarymanagementsystem.repository.AuteurRepository;
import com.lms.librarymanagementsystem.repository.DBUserRepository;
import com.lms.librarymanagementsystem.repository.LivreRepository;
import com.lms.librarymanagementsystem.service.api.LivreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class DataInitializer implements CommandLineRunner {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DataInitializer.class);
    @Autowired
    private  LivreService livreService;
    private final AuteurRepository auteurRepository;
    private final DBUserRepository dbUserRepository;

    public DataInitializer(LivreRepository livreRepository, AuteurRepository auteurRepository, DBUserRepository dbUserRepository) {
        this.auteurRepository = auteurRepository;
        this.dbUserRepository = dbUserRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Création d'un auteur
        Auteur auteur = new Auteur();
        auteur.setNom("Nom de l'Auteur");
        auteur.setPrenom("Prénom de l'Auteur");
        auteur.setNationalite("Nationalité de l'Auteur");
        // Ajoutez d'autres propriétés de l'auteur
        auteurRepository.save(auteur);
        log.info("Auteur créé avec succès");

        // Création d'un utilisateur
        DBUser dbUser = new DBUser();
        // N'utilisez pas de mots de passe en texte brut dans un environnement de production
        dbUser.setUsername("admin");
        dbUser.setPassword("admin");
        dbUser.setRole("ADMIN");
        // Ajoutez d'autres propriétés de l'utilisateur
        dbUserRepository.save(dbUser);
        log.info("Utilisateur créé avec succès");

        // Création d'un livre
        Livre livre = new Livre();
        livre.setIsbn("ISBN123456");
        livre.setTitre("Titre du Livre");
        livre.setAuteur(auteur);
        livre.setAddedBy(dbUser);
        livreService.save(livre);

        // Création d'exemplaires pour le livre
        Exemplaire exemplaire1 = new Exemplaire();
        exemplaire1.setStatus("Disponible");
        exemplaire1.setDateDeRetourPrevue(LocalDate.now().plusDays(14));



        Exemplaire exemplaire2 = new Exemplaire();
        exemplaire2.setStatus("Disponible");
        exemplaire2.setDateDeRetourPrevue(LocalDate.now().plusDays(14));


        livre.ajouterExemplaire(exemplaire1);
        livre.ajouterExemplaire(exemplaire2);

        // Enregistrement du livre
        livreService.save(livre);
        log.info("Livre créé avec succès");
    }
}
