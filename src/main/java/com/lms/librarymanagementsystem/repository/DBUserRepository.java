package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
	public DBUser findByUsername(String username);
//	Optional<DBUser> findByUsername(String username);
}