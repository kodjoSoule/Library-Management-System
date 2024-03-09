package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.Adherent;
import com.lms.librarymanagementsystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);


}
