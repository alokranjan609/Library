package com.example.BookandAuthor.BookAndAuthor.controllers;

import com.example.BookandAuthor.BookAndAuthor.dto.AuthorDTO;
import com.example.BookandAuthor.BookAndAuthor.services.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {


    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.createAuthor(authorDTO);
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

//    @GetMapping("/search")
//    public List<Author> searchAuthor(@RequestParam String name) {
//        return authorService.findByName(name);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteAuthor(@PathVariable Long id) {
//        authorService.deleteAuthor(id);
//    }
}
