package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Exemplaire;
import com.lms.librarymanagementsystem.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {

}
