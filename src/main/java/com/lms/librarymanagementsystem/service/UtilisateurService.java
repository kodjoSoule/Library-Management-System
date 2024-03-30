package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Auteur;
import com.lms.librarymanagementsystem.model.Utilisateur;
import com.lms.librarymanagementsystem.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public void saveUser(Utilisateur user) {
        utilisateurRepository.save(user);
    }

    public Utilisateur getUserById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public void updateUser(Long id, Utilisateur user) {
        utilisateurRepository.findById(id).ifPresent(existingUser -> {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            utilisateurRepository.save(existingUser);
        });
    }

    public void deleteUser(Long id) {
        utilisateurRepository.deleteById(id);
    }


    public Page<Utilisateur> findPaginated(Pageable Pageable) {
        return utilisateurRepository.findAll(Pageable);

    }

    public boolean existsById(long userId) {
        return utilisateurRepository.existsById(userId);
    }

    public Utilisateur getUtilisateurById(int i) {
        return utilisateurRepository.findById((long) i).get();
    }

    public void saveUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Object countUtilisateurs() {
        return utilisateurRepository.count();
    }

    public Object countUtilisateursRoleAdmin() {
        return utilisateurRepository.countUtilisateursByRole("ADMIN");
    }

    public void updateUser(Utilisateur user) {
        utilisateurRepository.save(user);
    }

    public Utilisateur findByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public List<Utilisateur> getUtilisateursByRole(String admin) {
        return utilisateurRepository.findUtilisateursByRole(admin);
    }
}