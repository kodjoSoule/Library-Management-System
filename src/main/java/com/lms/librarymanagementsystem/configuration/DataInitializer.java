package com.lms.librarymanagementsystem.configuration;

import com.lms.librarymanagementsystem.model.*;
import com.lms.librarymanagementsystem.repository.LivreRepository;
import com.lms.librarymanagementsystem.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.List;



@Component
public class DataInitializer implements CommandLineRunner {


    @Autowired
    private Environment env;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DataInitializer.class);
    @Autowired
    private  LivreService livreService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CategorieService categorieService;

    @Autowired
    private UtilisateurService UtilisateurService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private EmpruntService empruntService;
    @Autowired
    private AuteurService auteurService;
    @Autowired
    ExamplaireService examplaireService;
    @Autowired
    InfosService infosService;
    @Autowired
    UtilisateurService utilisateurService;

    //Create function to return file image  from C:\Users\Kodjo\lmages\profile.jpg
    public File getImageFromSystem(){
        String path = "C:\\Users\\Kodjo\\lmages\\profile.jpg";
        File file = new File(path);
        return file;
    }
    public  void createUtilisateurs(){
        for (int i = 0; i < 10; i++) {
            Utilisateur user = new Utilisateur();
            user.setUsername("user" + i);
            user.setEmail("user" + i + "@example.com");
            user.setFirstName("User" + i);
            user.setLastName("Test");
            user.setPassword("password" + i);
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            utilisateurService.saveUser(user);
        }
    }
    public void createLivreTest(){
        // Création d'une catégorie
        Categorie romanCategory = new Categorie();
        romanCategory.setNom("Roman");
        romanCategory.setDescription("Catégorie de livres de romans");
        categorieService.saveCategorie(romanCategory);
        log.info("Catégorie créée avec succès");
        //Création d'un auteur
        Auteur victorHugo = new Auteur();
        victorHugo.setNom("Victor");
        victorHugo.setPrenom("Hugo");
        auteurService.saveAuteur(victorHugo);
        log.info("Auteur "+victorHugo.getNom() +" créé avec succès");
        // Création d'un administrateur
        Admin soule = new Admin();
        soule.setNom("Souleymane");
        soule.setPrenom("Diallo")  ;
        soule.setEmail("soule@example.com");
        soule.setUsername("soule");
        soule.setPassword("soule");
        adminService.saveAdmin(soule);
        log.info("Administrateur "+soule.getUsername() +" créé avec succès");

        // Définir le nombre de livres à créer
        int nombreDeLivres = 35; // Modifier ce nombre selon vos besoins
        for (int i = 0; i < nombreDeLivres; i++) {
            // Création d'un nouveau livre
            Livre livre = new Livre();
            livre.setIsbn("ISBN" + (i + 1)); // ISBN unique pour chaque livre
            livre.setTitre("Titre du Livre " + (i + 1)); // Titre unique pour chaque livre
            livre.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac purus sit amet nunc fermentum aliquam. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet."+
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac purus sit amet nunc fermentum aliquam. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet." +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac purus sit amet nunc fermentum aliquam. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet."); // Description commune pour tous les livres
            livre.setLangue("Français"); // Langue commune pour tous les livres
            livre.setNbPages(200); // Nombre de pages commun pour tous les livres
            livre.setDatePublication(LocalDate.of(2002, 12, 12)); // Date de publication commune pour tous les livres
            livre.setEditeur("Editions du Baobab"); // Éditeur commun pour tous les livres
            livre.setAuteur(victorHugo); // Utilisation du même auteur pour tous les livres
            livre.setAddedBy(soule); // Utilisateur qui ajoute le livre
            // Création d'une image pour le livre
            ImageData imageData = new ImageData();
            imageData.setName( "no_cover.jpg");
            imageData.setType("image/png");
            imageData.setFilePath("images/" + imageData.getName());
            //imageService.saveImage(imageData);
            livre.setImage(imageData);
            livre.setCategorie(romanCategory); // Catégorie commune pour tous les livres
            // Enregistrement du livre
            livreService.saveLivre(livre);

            // Ajout d'exemplaires au livre
            livreService.addExemplaireParNombre(livre, 5); // Ajout de 5 exemplaires à chaque livre
            livreService.saveLivre(livre); // Sauvegarde du livre après ajout des exemplaires

            // Affichage d'un message pour indiquer que le livre a été créé avec succès
            log.info("Livre " + (i + 1) + " créé avec succès");
        }
    }
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //default user
        //userService.createDefaultUser(new User(null, "admin", "admin", passwordEncoder().encode("password"), new ArrayList<>(Collections.singletonList("ROLE_ADMIN"))));

        createUtilisateurs();
        //infos
        Infos infos = new Infos();
        infos.setHeuresOuverture("Lundi - Vendredi: 8h - 18h, Samedi: 9h - 17h, Dimanche: Fermé");
        infos.setReglements("Règlements de la bibliothèque");
        infos.setMessagesBanniere("Messages de bannière");
        infos.setConditionEmprunt("Conditions d'emprunt");
        infos.setPenelites("Pénélités");
        infosService.updateInfos(infos);
        createLivreTest();
        //1 Création d'un catégorie
        Categorie scienceFictionCategory = new Categorie();
        scienceFictionCategory.setNom("Science Fiction");
        scienceFictionCategory.setDescription("Catégorie de livres de science fiction");
        categorieService.saveCategorie(scienceFictionCategory);
        log.info("Catégorie créée avec succès");
        //2 Création d'un auteur
        Auteur auteur = new Auteur();
        auteur.setNom("Kouyaté");
        auteur.setPrenom("Seydou Bandja");
        //test de creation de 10 auteurs
        for (int i = 10; i < 30; i++) {
            Auteur auteur1 = new Auteur();
            auteur1.setNom("Nom Auteur "+i);
            auteur1.setPrenom("Prenom Auteur "+i);
            auteur1.setDateNaissance(LocalDate.of(1990, 12, 12));
            auteurService.saveAuteur(auteur1);
        }

        auteurService.saveAuteur(auteur);
        log.info("Auteur créé avec succès");
        //3 Création d'un utilisateur
        Admin administrateur = new Admin();
        administrateur.setUsername("djiguiba");
        administrateur.setPassword("djiguiba");
        administrateur.setNom("Djiguiba");
        administrateur.setPrenom("Kouyaté");
        administrateur.setEmail("Djiguiba@example.com");
        adminService.saveAdmin(administrateur);
        log.info("Utilisateur créé avec succès");

        //4 Création d'un livre
        Livre sousOrange = new Livre();
        sousOrange.setIsbn("ISBN123456");
        sousOrange.setTitre("Sous Orange");
        sousOrange.setLangue("Français");
        sousOrange.setNbPages(200);
        sousOrange.setDatePublication(LocalDate.of(2002, 12, 12));
        sousOrange.setEditeur("Editions du Baobab");
        sousOrange.setAuteur(auteur);
        sousOrange.setAddedBy(administrateur);
        //date de publication 2002-12-12
        sousOrange.setDatePublication(
                LocalDate.of(2002, 12, 12)
        );
        ImageData coverImage = new ImageData();
        coverImage.setName("no_cover.png");
        sousOrange.setImage(coverImage);
        sousOrange.setCategorie(scienceFictionCategory);
        //sousOrange.setExemplaires(List.of(exemplaire1, exemplaire2));
        //sousOrange.addExemplaireParNombre(10);
        livreService.saveLivre(sousOrange);
        livreService.addExemplaireParNombre(sousOrange, 5);
        livreService.saveLivre(sousOrange);
        log.info("Livre créé avec succès");
        //5 creation d'un Utilisateur
        Utilisateur Utilisateur = new Utilisateur();
        Utilisateur.setLastName("Coulibaly");
        Utilisateur.setFirstName("Mamadou");
        Utilisateur.setUsername("mamadou");
        Utilisateur.setPassword("mamadou");
        UtilisateurService.saveUtilisateur(Utilisateur);
        log.info("Adhérent créé avec succès");

        if(sousOrange.getExemplaires().isEmpty()){
            log.info("Aucun exemplaire disponible pour l'emprunt");
        }else {
            //6 creation d'un emprunt
            Emprunt emprunt = new Emprunt();
            emprunt.setUtilisateur(Utilisateur);
            Exemplaire exemplaire1 = sousOrange.getExemplaires().get(0);
            emprunt.setExemplaire(exemplaire1);
            emprunt.setDateEmprunt(LocalDate.now());
            emprunt.setDateRetourPrevue(LocalDate.now().plusDays(14)); // Retour prévu dans 14 jours
            empruntService.saveEmprunt(emprunt);
            log.info("Emprunt1 créé avec succès");

            Emprunt emprunt2 = new Emprunt();
            emprunt2.setUtilisateur(Utilisateur);
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
            log.info("Image : " + livre.getImage().getFilePath());
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
            List<Utilisateur> utilisateurs = UtilisateurService.getAllUtilisateurs();
            for (Utilisateur utilisateur1 : utilisateurs) {
                log.info("Adhérent : " + utilisateur1.getFullName());
                //liste des emprunts
                List<Emprunt> emprunts = empruntService.getEmpruntsByUtilisateur(utilisateur1);
                if (emprunts.isEmpty()) {
                    log.info("Aucun emprunt pour cet adhérent");
                }else {
                    for (Emprunt emprunt1 : emprunts) {
                        log.info("Emprunt : " + emprunt1.getId() + ", Livre : " + emprunt1.getExemplaire().getLivre().getTitre() + ", Date de Retour Prévue : " + emprunt1.getDateRetourPrevue());
                    }
                }
                //liste des retours de livres de adhérent
                List<Emprunt> retours = empruntService.getRetoursByUtilisateur(utilisateur1);
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


        // Création d'un adhérent
        Utilisateur Utilisateur1 = new Utilisateur();
        Utilisateur.setFirstName("Nom de l'Adhérent");
        Utilisateur.setLastName("Prénom de l'Adhérent");
        Utilisateur.setUsername("Utilisateur");
        Utilisateur.setPassword("Utilisateur");
        UtilisateurService.saveUtilisateur(Utilisateur1);
        // Création d'un administrateur
        Admin admin1 = new Admin();
        admin1.setNom("Nom de l'Administrateur");
        admin1.setPrenom("Prénom de l'Administrateur");
        admin1.setUsername("admin");
        admin1.setPassword("admin");
        adminService.saveAdmin(admin1);

        // Emprunt d'un exemplaire par l'administrateur à l'adhérent
        Emprunt emprunt = new Emprunt();
        emprunt.setUtilisateur(Utilisateur);
        emprunt.setExemplaire(sousOrange.getExemplaires().get(1)); // Remplacez 1L par l'ID du livre réel
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetourPrevue(LocalDate.now().plusDays(14)); // Retour prévu dans 14 jours
        empruntService.saveEmprunt(emprunt);
        // Affichage de la liste des emprunts de l'adhérent
        //GetUtilisateur parID
        Utilisateur Utilisateur2 = UtilisateurService.getUtilisateurById(1);

        List<Emprunt> empruntsUtilisateur = empruntService.getEmpruntsByUtilisateur(Utilisateur2);
        System.out.println("Liste des emprunts de l'adhérent :");
        for (Emprunt e : empruntsUtilisateur) {
            System.out.println("ID Emprunt : " + e.getId() +
                    ", Livre : " + e.getExemplaire().getLivre().getTitre() +
                    ", Date de Retour Prévue : " + e.getDateRetourPrevue());
        }
        // Exemple d'emprunt par l'administrateur à l'adhérent
        Emprunt empruntAdmin = new Emprunt();
        empruntAdmin.setUtilisateur(Utilisateur);
        empruntAdmin.setExemplaire(sousOrange.getExemplaires().get(1)); // Remplacez 2L par l'ID du livre réel
        empruntAdmin.setDateEmprunt(LocalDate.now());
        empruntAdmin.setDateRetourPrevue(LocalDate.now().plusDays(14)); // Retour prévu dans 14 jours
        empruntService.saveEmprunt(empruntAdmin);

        // Affichage de la liste des emprunts de l'adhérent après l'emprunt par l'administrateur
        List<Emprunt> empruntsUtilisateurApresAdmin = empruntService.getEmpruntsByUtilisateur(Utilisateur1);
        System.out.println("Liste des emprunts de l'adhérent après l'emprunt par l'administrateur :");
        for (Emprunt e : empruntsUtilisateurApresAdmin) {
            System.out.println("ID Emprunt : " + e.getId() +
                    ", Livre : " + e.getExemplaire().getLivre().getTitre() +
                    ", Date de Retour Prévue : " + e.getDateRetourPrevue());
        }

        //show address of server and port
        log.info("Server started +"+env.getProperty("server.host") +"on port "+env.getProperty("server.port"));


    }
}
