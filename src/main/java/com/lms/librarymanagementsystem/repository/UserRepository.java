package com.lms.librarymanagementsystem.repository;

import com.lms.librarymanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    public User save(User user);
    public List<User> findAll();
    public Optional<User> findById(Long id);
    public void deleteById(Long id);
    public User findByEmail(String email);


}
