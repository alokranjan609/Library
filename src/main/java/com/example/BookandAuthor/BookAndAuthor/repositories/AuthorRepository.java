package com.example.BookandAuthor.BookAndAuthor.repositories;

import com.example.BookandAuthor.BookAndAuthor.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    List<AuthorEntity> findByNameContainingIgnoreCase(String name);
}
