package com.lms.librarymanagementsystem.configuration;

import com.lms.librarymanagementsystem.model.*;
import com.lms.librarymanagementsystem.repository.AuteurRepository;
import com.lms.librarymanagementsystem.repository.DBUserRepository;
import com.lms.librarymanagementsystem.repository.LivreRepository;
import com.lms.librarymanagementsystem.service.ImageService;
import com.lms.librarymanagementsystem.service.LivreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
@Component
public class DataInitializer implements CommandLineRunner {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DataInitializer.class);
    @Autowired
    private  LivreService livreService;
    @Autowired
    private ImageService imageService;
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
        // Création d'une image
        Image image = generateImage();
        // Création d'un livre
        Livre livre = new Livre();
        livre.setIsbn("ISBN123456");
        livre.setTitre("Titre du Livre 1");
        livre.setAuteur(auteur);
        livre.setAddedBy(dbUser);
        livre.setImage(image);
        livreService.save(livre);

        //fait boucle pour creer 20 livres
        for (int i = 5; i < 20; i++) {
            Livre livre1 = new Livre();
            livre1.setIsbn("ISBN12345"+i);
            livre1.setTitre("Titre du Livre "+i);
            livre1.setAuteur(auteur);
            livre1.setAddedBy(dbUser);
            Image image1 = generateImage();
            livre1.setImage(image1);
            livreService.save(livre1);
        }

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

    public Image generateImage(){
        Image image = new Image();
        image.setName("image1");
        try {


            // Charger une image depuis Internet
            //url image books
            String urlImage = "https://th.bing.com/th/id/R.4a53840dde2a1bfa6b69d92606514fc7?rik=AOGJTqt4MCFpog&pid=ImgRaw&r=0";
            URL url = new URL(urlImage);
            InputStream inputStream = url.openStream();
            byte[] imageData = inputStream.readAllBytes();
            image.setImage(imageData);

            // Définition des dates
            LocalDate currentDate = LocalDate.now();
            image.setAddedAt(currentDate);
            image.setUpdateAt(currentDate);

            // Enregistrement de l'image
            imageService.save(image);
            return image;

        } catch (IOException e) {
            log.error("Impossible de charger l'image depuis le disque local", e);
            throw new RuntimeException("Impossible de charger l'image depuis le disque local", e);
        }
    }
}
