package com.example.BookandAuthor.BookAndAuthor.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BookDTO {

    private Long id;
    private String title;
    private LocalDate publishedDate;
    private Long authorId;
    private String authorName;

}
