package com.lms.librarymanagementsystem.model;

import java.util.List;

public class ReportData {
    private List<String> categories; // par exemple, les dates
    private List<Integer> totalEmprunts; // nombre total d'emprunts par période
    private List<Integer> empruntsRetournes; // nombre d'emprunts retournés par période
    private List<Integer> empruntsEnCours; // nombre d'emprunts en cours par période

    // Getters et Setters
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Integer> getTotalEmprunts() {
        return totalEmprunts;
    }

    public void setTotalEmprunts(List<Integer> totalEmprunts) {
        this.totalEmprunts = totalEmprunts;
    }

    public List<Integer> getEmpruntsRetournes() {
        return empruntsRetournes;
    }

    public void setEmpruntsRetournes(List<Integer> empruntsRetournes) {
        this.empruntsRetournes = empruntsRetournes;
    }

    public List<Integer> getEmpruntsEnCours() {
        return empruntsEnCours;
    }

    public void setEmpruntsEnCours(List<Integer> empruntsEnCours) {
        this.empruntsEnCours = empruntsEnCours;
    }

    // Vous pouvez ajouter un constructeur et d'autres méthodes utiles si nécessaire
}