package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Emprunt;
import com.lms.librarymanagementsystem.model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    List<Emprunt> findByUtilisateur(Utilisateur user1);
    public Optional<Emprunt> findById(Long id);
    void deleteById(Long id);


    List<Emprunt> findByUtilisateurAndRetourne(Utilisateur utilisateur1, boolean b);

    //sql query SELECT COUNT(*) FROM Emprunt e WHERE e.date_retour_prevue < CURRENT_DATE AND e.retourne = false
    @Query("SELECT COUNT(e) FROM Emprunt e WHERE e.dateRetourPrevue < CURRENT_DATE AND e.retourne = false")
    int countEmpruntsEnRetard();

    Page<Emprunt> findByRetourne(boolean b, Pageable pageable);
    @Query("SELECT e FROM Emprunt e ORDER BY e.dateEmprunt DESC")
    List<Emprunt> findThreeRecentEmprunts(Pageable pageable);

    @Query("SELECT e FROM Emprunt e WHERE e.dateRetourPrevue < :currentDate AND e.retourne = false")
    List<Emprunt> findRetardsEmprunts(@Param("currentDate") LocalDate currentDate);
}
