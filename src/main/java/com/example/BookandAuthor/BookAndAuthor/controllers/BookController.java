package com.example.BookandAuthor.BookAndAuthor.controllers;

import com.example.BookandAuthor.BookAndAuthor.dto.BookDTO;
import com.example.BookandAuthor.BookAndAuthor.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {


    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/{authorId}")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO, @PathVariable Long authorId) {
        return new ResponseEntity<>(bookService.saveBook(bookDTO, authorId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {

        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<BookDTO>> findByTitle(@RequestParam String title) {

        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @GetMapping("/search/date")
    public ResponseEntity<List<BookDTO>> findAfterDate(@RequestParam String date) {

        return ResponseEntity.ok(bookService.findByDate(LocalDate.parse(date)));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookDTO>> findByAuthor(@PathVariable Long authorId) {

        return ResponseEntity.ok(bookService.findByAuthor(authorId));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);
        ResponseEntity.noContent().build();
    }
}