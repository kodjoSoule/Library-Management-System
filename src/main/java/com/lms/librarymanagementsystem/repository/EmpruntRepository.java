package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Adherent;
import com.lms.librarymanagementsystem.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByAdherent(Adherent adherent1);
    public Optional<Emprunt> findById(Long id);
    void deleteById(Long id);



}
