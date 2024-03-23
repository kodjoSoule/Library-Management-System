package com.lms.librarymanagementsystem.model;

import jakarta.persistence.*;


@Entity
public class Infos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Heures d'ouverture de la bibliothèque
    @Column(columnDefinition = "TEXT")
    private String heuresOuverture;
    // Exemple: Lundi - Vendredi: 8h - 18h, Samedi: 9h - 17h, Dimanche: Fermé

    // Règlements de la bibliothèque
    @Column(columnDefinition = "TEXT")
    private String reglements;

    // Messages de bannière@Lob
    @Column(columnDefinition = "TEXT")
    private String messagesBanniere;

    @Column(columnDefinition = "TEXT")
    private String conditionEmprunt;

    @Column(columnDefinition = "TEXT")
    private String penelites;


    public String getConditionEmprunt() {
        return conditionEmprunt;
    }

    public void setConditionEmprunt(String conditionEmprunt) {
        this.conditionEmprunt = conditionEmprunt;
    }

    public String getPenelites() {
        return penelites;
    }

    public void setPenelites(String penelites) {
        this.penelites = penelites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeuresOuverture() {
        return heuresOuverture;
    }

    public void setHeuresOuverture(String heuresOuverture) {
        this.heuresOuverture = heuresOuverture;
    }

    public String getReglements() {
        return reglements;
    }

    public void setReglements(String reglements) {
        this.reglements = reglements;
    }

    public String getMessagesBanniere() {
        return messagesBanniere;
    }

    public void setMessagesBanniere(String messagesBanniere) {
        this.messagesBanniere = messagesBanniere;
    }
}
