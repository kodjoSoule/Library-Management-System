package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Infos;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InfosRepository extends JpaRepository<Infos, Long> {
	// Ajoutez des méthodes personnalisées si nécessaire
    //getFirstInfos
    Infos findFirstByOrderByIdAsc();
}
