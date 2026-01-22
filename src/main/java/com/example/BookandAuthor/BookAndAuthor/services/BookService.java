package com.example.BookandAuthor.BookAndAuthor.services;

import com.example.BookandAuthor.BookAndAuthor.dto.AuthorDTO;
import com.example.BookandAuthor.BookAndAuthor.dto.BookDTO;
import com.example.BookandAuthor.BookAndAuthor.entities.AuthorEntity;
import com.example.BookandAuthor.BookAndAuthor.entities.BookEntity;
import com.example.BookandAuthor.BookAndAuthor.exceptions.ResourceNotFoundException;
import com.example.BookandAuthor.BookAndAuthor.repositories.AuthorRepository;
import com.example.BookandAuthor.BookAndAuthor.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }


    public BookDTO saveBook(BookDTO bookDTO, Long authorId) {
        BookEntity bookEntity=modelMapper.map(bookDTO,BookEntity.class);
        AuthorEntity authorEntity = authorRepository.findById(authorId).orElseThrow(()->new ResourceNotFoundException("No author found"));
        bookEntity.setAuthor(authorEntity);
        return modelMapper.map(bookRepository.save(bookEntity),BookDTO.class);
    }

    public List<BookDTO> getAllBooks() {
        List<BookEntity> bookEntities=bookRepository.findAll();
        return bookEntities
                .stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookDTO.class))
                .collect(Collectors.toList());
    }



    public BookDTO getBookById(Long id) {
        if(!bookRepository.existsById(id)){
            throw new ResourceNotFoundException("Book not found with this id");
        }
        return modelMapper.map(bookRepository.findById(id),BookDTO.class);

    }

    public List<BookDTO> findByTitle(String title) {
        List<BookEntity> bookEntities=bookRepository.findByTitleContainingIgnoreCase(title);
        return bookEntities
                .stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookDTO.class))
                .collect(Collectors.toList());
    }

    public List<BookDTO> findByDate(LocalDate date) {
        List<BookEntity> bookEntities=bookRepository.findByPublishedDateAfter(date);
        return bookEntities
                .stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookDTO.class))
                .collect(Collectors.toList());
    }

    public List<BookDTO> findByAuthor(Long authorId) {
        List<BookEntity> bookEntities=bookRepository.findByAuthor_Id(authorId);
        return bookEntities
                .stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            throw new ResourceNotFoundException("No book found for this id");
        }
        bookRepository.deleteById(id);
    }
}

