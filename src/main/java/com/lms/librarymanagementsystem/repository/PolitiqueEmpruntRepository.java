package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.DBUser;
import com.lms.librarymanagementsystem.model.PolitiqueEmprunt;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PolitiqueEmpruntRepository extends JpaRepository<PolitiqueEmprunt, Long> {
	// Ajoutez des méthodes personnalisées si nécessaire
}
