package com.example.BookandAuthor.BookAndAuthor.repositories;

import com.example.BookandAuthor.BookAndAuthor.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByTitleContainingIgnoreCase(String title);

    List<BookEntity> findByPublishedDateAfter(LocalDate date);

    List<BookEntity> findByAuthor_Id(Long authorId);
}
