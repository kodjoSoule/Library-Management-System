package com.lms.librarymanagementsystem.configuration;

import com.lms.librarymanagementsystem.model.*;
import com.lms.librarymanagementsystem.repository.AuteurRepository;
import com.lms.librarymanagementsystem.repository.DBUserRepository;
import com.lms.librarymanagementsystem.repository.LivreRepository;
import com.lms.librarymanagementsystem.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;



@Component

public class DataInitializer implements CommandLineRunner {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DataInitializer.class);
    @Autowired
    private  LivreService livreService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CategorieService categorieService;

    @Autowired
    private AdherantService adherentService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private EmpruntService empruntService;
    private final AuteurRepository auteurRepository;
    private final DBUserRepository dbUserRepository;
    @Autowired
    ExamplaireService examplaireService;

    public DataInitializer(LivreRepository livreRepository, AuteurRepository auteurRepository, DBUserRepository dbUserRepository) {
        this.auteurRepository = auteurRepository;
        this.dbUserRepository = dbUserRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //1 Création d'un catégorie
        Categorie scienceFictionCategory = new Categorie();
        scienceFictionCategory.setNom("Science Fiction");
        categorieService.saveCategorie(scienceFictionCategory);
        log.info("Catégorie créée avec succès");

        //2 Création d'un auteur
        Auteur auteur = new Auteur();
        auteur.setNom("Kouyaté");
        auteur.setPrenom("Seydou Bandja");
        auteurRepository.save(auteur);
        log.info("Auteur créé avec succès");

        //3 Création d'un utilisateur
        Admin administrateur = new Admin();
        administrateur.setUsername("djiguiba");
        administrateur.setPassword("djiguiba");
        adminService.saveAdmin(administrateur);
        log.info("Utilisateur créé avec succès");

        //4 Création d'un livre
        Livre sousOrange = new Livre();
        sousOrange.setIsbn("ISBN123456");
        sousOrange.setTitre("Sous Orange");
        sousOrange.setAuteur(auteur);
        sousOrange.setAddedBy(administrateur);
        //date de publication 2002-12-12
        sousOrange.setDatePublication(
                LocalDate.of(2002, 12, 12)
        );
        ImageLivre sousOrangeImage = new ImageLivre();
        sousOrangeImage.setName("sous-orange.jpg");
        sousOrange.setImage(sousOrangeImage);
        sousOrange.setCategorie(scienceFictionCategory);
        //sousOrange.setExemplaires(List.of(exemplaire1, exemplaire2));
        //sousOrange.addExemplaireParNombre(10);
        livreService.saveLivre(sousOrange);
        livreService.addExemplaireParNombre(sousOrange, 5);
        livreService.saveLivre(sousOrange);
        log.info("Livre créé avec succès");
        //5 creation d'un adherent
        Adherent adherent = new Adherent();
        adherent.setNom("Coulibaly");
        adherent.setPrenom("Mamadou");
        adherent.setUsername("mamadou");
        adherent.setPassword("mamadou");
        adherentService.saveAdherent(adherent);
        log.info("Adhérent créé avec succès");

        if(sousOrange.getExemplaires().isEmpty()){
            log.info("Aucun exemplaire disponible pour l'emprunt");
        }else {
            //6 creation d'un emprunt
            Emprunt emprunt = new Emprunt();
            emprunt.setAdherent(adherent);
            Exemplaire exemplaire1 = sousOrange.getExemplaires().get(0);
            emprunt.setExemplaire(exemplaire1);
            emprunt.setDateEmprunt(LocalDate.now());
            emprunt.setDateRetourPrevue(LocalDate.now().plusDays(14)); // Retour prévu dans 14 jours
            empruntService.saveEmprunt(emprunt);
            log.info("Emprunt1 créé avec succès");

            Emprunt emprunt2 = new Emprunt();
            emprunt2.setAdherent(adherent);
            Exemplaire exemplaire2 = sousOrange.getExemplaires().get(1);
            emprunt2.setExemplaire(exemplaire2);
            emprunt2.setDateEmprunt(LocalDate.now());
            emprunt2.setDateRetourPrevue(LocalDate.now().plusDays(14)); // Retour prévu dans 14 jours
            empruntService.saveEmprunt(emprunt2);
            log.info("Emprunt 2 créé avec succès");
            //8 creation d'un retour d'emprunt 1
            emprunt.setRetourne(true);
            emprunt.setDateRetourEffectif(LocalDate.now());
            emprunt.getExemplaire().setStatus("Disponible");
            empruntService.saveEmprunt(emprunt);
            log.info("Retour d'emprunt créé avec succès");
        }
        //liste des livres avec des details
        List<Livre> livres = livreService.getAllLivres();
        for (Livre livre : livres) {
            log.info("Livre : " + livre.getTitre() + ", Auteur : " + livre.getAuteur().getNom() + " " + livre.getAuteur().getPrenom());
            //details admin
            log.info("Ajouté par : " + livre.getAddedBy().getUsername());
            //auteurt details
            log.info("Auteur : " + livre.getAuteur().getNom() + " " + livre.getAuteur().getPrenom());
            //categorie deetails
            log.info("Catégorie : " + livre.getCategorie().getNom());
            //image details
            log.info("Image : " + livre.getImage().getName());
            //nombre d'exemplaires
            log.info("Nombre d'exemplaires : " + livre.getExemplaires().size());
            //nombre d'exemplaires disponibles
            log.info("Nombre d'exemplaires disponibles : " +
                    livre.getExemplaires().stream().filter(exemplaire -> exemplaire.estDisponible()).count()
            );

            //details d'exemplaires
            for (Exemplaire exemplaire : livre.getExemplaires()) {
                log.info("Exemplaire : " + exemplaire.getId() + ", Status : " + exemplaire.getStatus());
            }
            //liste des adhérents
            List<Adherent> adherents = adherentService.getAllAdherants();
            for (Adherent adherent1 : adherents) {
                log.info("Adhérent : " + adherent1.getNom() + " " + adherent1.getPrenom());
                //liste des emprunts
                List<Emprunt> emprunts = empruntService.getEmpruntsByAdherent(adherent1);
                if (emprunts.isEmpty()) {
                    log.info("Aucun emprunt pour cet adhérent");
                }else {
                    for (Emprunt emprunt1 : emprunts) {
                        log.info("Emprunt : " + emprunt1.getId() + ", Livre : " + emprunt1.getExemplaire().getLivre().getTitre() + ", Date de Retour Prévue : " + emprunt1.getDateRetourPrevue());
                    }
                }
                //liste des retours de livres de adhérent
                List<Emprunt> retours = empruntService.getRetoursByAdherent(adherent1);
                if(retours.isEmpty()){
                    log.info("Aucun retour pour cet adhérent");
                }else {
                    for (Emprunt retour : retours) {
                        log.info("Retour : " + retour.getId() + ", Livre : " + retour.getExemplaire().getLivre().getTitre() + ", Date de Retour : " + retour.getDateRetourEffectif());

                    }
                }
            }
            //nombre d'exemplaires
            log.info("Nombre d'exemplaires : " + livre.getExemplaires().size());
            //details d'exemplaires
            for (Exemplaire exemplaire : livre.getExemplaires()) {
                log.info("Exemplaire : " + exemplaire.getId() + ", Status : " + exemplaire.getStatus());
            }

        }


//        // Création d'un adhérent
//        Adherent adherent = new Adherent();
//        adherent.setNom("Nom de l'Adhérent");
//        adherent.setPrenom("Prénom de l'Adhérent");
//        adherent.setUsername("adherent");
//        adherent.setPassword("adherent");
//
//        adherentService.saveAdherent(adherent);
//        // Création d'un administrateur
//        Admin admin1 = new Admin();
//        admin1.setNom("Nom de l'Administrateur");
//        admin1.setPrenom("Prénom de l'Administrateur");
//        admin1.setUsername("admin");
//        admin1.setPassword("admin");
//        adminService.saveAdmin(admin1);
//        //creation d'un livre
//        Livre livre2 = new Livre();
//        livre2.setIsbn("ISBN123456");
//        livre2.setTitre("Titre du Livre 2 Empruntee");
//        livre2.setAuteur(auteur);
//        livre2.setAddedBy(admin1);
//        livre2.setImage(imageLivre);
//        //date de publication 2002-12-12
//        livre2.setDatePublication(LocalDate.of(2002, 12, 12));
//        livre2.setCategorie(category1);
//        livre2.setExemplaires(List.of(exemplaire1, exemplaire2));
//        livreService.save(livre2);
//        // Création d'exemplaires pour le livre
//        Exemplaire exemplaire3 = new Exemplaire();
//        exemplaire3.setStatus("Emprunté");
//        exemplaire3.setDateDeRetourPrevue(LocalDate.now().plusDays(14));
//




        // Emprunt d'un exemplaire par l'administrateur à l'adhérent
//        Emprunt emprunt = new Emprunt();
//        emprunt.setAdherent(adherent);
//        emprunt.setExemplaire(exemplaire3); // Remplacez 1L par l'ID du livre réel
//        emprunt.setDateEmprunt(LocalDate.now());
//        emprunt.setDateRetourPrevue(LocalDate.now().plusDays(14)); // Retour prévu dans 14 jours
//        empruntService.saveEmprunt(emprunt);
//
        // Affichage de la liste des emprunts de l'adhérent
        //GetAdherant parID
//        Adherent adherent1 = adherentService.getAdherantById(1);

//        List<Emprunt> empruntsAdherent = empruntService.getEmpruntsByAdherent(adherent1);
//        System.out.println("Liste des emprunts de l'adhérent :");
//        for (Emprunt e : empruntsAdherent) {
//            System.out.println("ID Emprunt : " + e.getId() +
//                    ", Livre : " + e.getExemplaire().getLivre().getTitre() +
//                    ", Date de Retour Prévue : " + e.getDateRetourPrevue());
//        }
//        // Exemple d'emprunt par l'administrateur à l'adhérent
//        Emprunt empruntAdmin = new Emprunt();
//        empruntAdmin.setAdherent(adherent);
//        empruntAdmin.setExemplaire(exemplaire3); // Remplacez 2L par l'ID du livre réel
//        empruntAdmin.setDateEmprunt(LocalDate.now());
//        empruntAdmin.setDateRetourPrevue(LocalDate.now().plusDays(14)); // Retour prévu dans 14 jours
//        empruntService.saveEmprunt(empruntAdmin);
//
//        // Affichage de la liste des emprunts de l'adhérent après l'emprunt par l'administrateur
//        List<Emprunt> empruntsAdherentApresAdmin = empruntService.getEmpruntsByAdherent(adherent1);
//        System.out.println("Liste des emprunts de l'adhérent après l'emprunt par l'administrateur :");
//        for (Emprunt e : empruntsAdherentApresAdmin) {
//            System.out.println("ID Emprunt : " + e.getId() +
//                    ", Livre : " + e.getExemplaire().getLivre().getTitre() +
//                    ", Date de Retour Prévue : " + e.getDateRetourPrevue());
//        }
    }
}
