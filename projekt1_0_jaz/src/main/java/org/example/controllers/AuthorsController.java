package org.example.controllers;

import org.example.model.Authors;
import org.example.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/authors/")

public class AuthorsController {

    AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsController(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    @GetMapping
    public ResponseEntity<List<Authors>> getAllAuthors() {
        return ResponseEntity.ok(authorsRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Authors> createAuthors(@RequestBody Authors authors) {

        Authors author = authorsRepository.save(authors);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Authors> getAuthorsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(authorsRepository.findById(id).orElse(null));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorsById(@PathVariable("id") Long id) {
        if (authorsRepository.findById(id).isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        authorsRepository.deleteById(id);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Authors> updateAuthors(@PathVariable("id") Long id, @RequestBody Authors authors) {
        if (authorsRepository.findById(id).isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(authorsRepository.save(authors));

    }
}

