package com.lms.librarymanagementsystem.repository;


import com.lms.librarymanagementsystem.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findAllByTitleContaining(String title);
}