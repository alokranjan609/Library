package com.example.BookandAuthor.BookAndAuthor.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate publishedDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;
}
