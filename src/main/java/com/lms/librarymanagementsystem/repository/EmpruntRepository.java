package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.User;
import com.lms.librarymanagementsystem.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByAdherent(User user1);
    public Optional<Emprunt> findById(Long id);
    void deleteById(Long id);
    List<Emprunt> findByAdherentAndRetourne(User user1, boolean b);
}
