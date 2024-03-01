package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
	public DBUser findByUsername(String username);
}