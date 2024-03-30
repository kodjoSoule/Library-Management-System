package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Emprunt;
import com.lms.librarymanagementsystem.model.Penalite;
import com.lms.librarymanagementsystem.model.Utilisateur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private MessageSender messageSender;
    @Autowired
    PenaliteService penaliteService;
    @Autowired
    UtilisateurService utilisateurService;


    //@Scheduled(cron = "0 0 * * * *")
    @Scheduled(fixedDelay = 5000)
    public void notifierRetardsEmprunts() {
        log.info("Exécution de la tâche planifiée pour notifier les retards d'emprunts");
        List<Emprunt> retardsEmprunts = empruntService.getRetardsEmprunts();
        for (Emprunt emprunt : retardsEmprunts) {

            if (emprunt.getUtilisateur() == null || emprunt.getUtilisateur().getEmail() == null) {
                log.info("Utilisateur ou email null");
                continue;
            }
            Utilisateur utilisateur = emprunt.getUtilisateur();
            Penalite penalite = new Penalite();
            if (utilisateur.getPenalite() == null) {
                penalite.setRaison("Retard de retour de livre");
                penalite.setNiveau(0);
                penalite.setUtilisateur(utilisateur);
                penaliteService.savePenalite(penalite);
                utilisateur.setPenalite(penalite);
            }
            if (utilisateur.getPenalite() != null) {
                if (utilisateur.getPenalite().getNiveau() == 1) {
                    penalite.setRaison("Retard de retour répété de livres");
                    penalite.setUtilisateur(utilisateur);
                    penalite.setNiveau(2);
                    penaliteService.savePenalite(penalite);
                    utilisateur.setPenalite(penalite);
                } else if (utilisateur.getPenalite().getNiveau() == 2) {
                    penalite.setRaison("Retard de retour continu de livres");
                    penalite.setUtilisateur(utilisateur);
                    penalite.setNiveau(3);
                    penaliteService.savePenalite(penalite);
                    utilisateur.setPenalite(penalite);
                } else if (utilisateur.getPenalite().getNiveau() == 3) {
                    penalite.setRaison("Suspension du compte suite à des retards répétés");
                    penalite.setUtilisateur(utilisateur);
                    penalite.setNiveau(4);
                    penaliteService.savePenalite(penalite);
                    utilisateur.setPenalite(penalite);
                }
            }
            utilisateurService.saveUtilisateur(utilisateur);
            String to = emprunt.getUtilisateur().getEmail();
            String subject = "Retard d'emprunt à la bibliothèque";
            String text = "Vous avez un retard d'emprunt pour le livre : " + emprunt.getLivre().getTitre();
            text = text + "\n" ;
            text = text + penalite.getRaison();
            messageSender.sendEmail(to, subject, text);
            log.info("Exécution de la tâche planifiée pour notifier les retards d'emprunts");
        }
    }
    // Exécuter cette méthode lorsque un compte est créé
    @Scheduled(fixedDelay = 5000)
    public void notifierCreationCompte() {

    }


    @Scheduled(fixedDelay = 5000)
    public void notifierEnvoiMessage() {

    }


    @Scheduled(fixedDelay = 10000)
    public void testerToutesLesDixSecondes() {
        // Logique pour exécuter le test
        log.info("Test exécuté à : " + LocalDateTime.now());
        List<Emprunt> retardsEmprunts = empruntService.getRetardsEmprunts();
        System.out.println("Nombre de retards d'emprunts : " + retardsEmprunts.size());
    }
}