package com.example.BookandAuthor.BookAndAuthor.services;

import com.example.BookandAuthor.BookAndAuthor.dto.AuthorDTO;
import com.example.BookandAuthor.BookAndAuthor.entities.AuthorEntity;
import com.example.BookandAuthor.BookAndAuthor.exceptions.ResourceNotFoundException;
import com.example.BookandAuthor.BookAndAuthor.repositories.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {


    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper=modelMapper;
    }

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        AuthorEntity authorEntity = modelMapper.map(authorDTO, AuthorEntity.class);
        return modelMapper.map(authorRepository.save(authorEntity), AuthorDTO.class);
    }

    public List<AuthorDTO> getAllAuthors() {
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        return authorEntities
                .stream()
                .map(authorEntity -> modelMapper.map(authorEntity, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long id) {
        AuthorEntity authorEntity= authorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No Author found for"+id));
        return modelMapper.map(authorEntity,AuthorDTO.class);
    }

    public List<AuthorDTO> findByName(String name) {
        List<AuthorEntity> authorEntities= authorRepository.findByNameContainingIgnoreCase(name);
        return authorEntities
                .stream()
                .map(authorEntity -> modelMapper.map(authorEntity, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteAuthor(Long id) {
        if(!authorRepository.existsById(id)){
            throw new ResourceNotFoundException("No Author found for "+id);
        }

        authorRepository.deleteById(id);
    }

}


