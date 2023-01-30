package org.example.controllers;
import org.example.model.Books;
import org.example.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books/")


public class BooksController {

    BooksRepository booksRepository;

    @Autowired
    public BooksController(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() {
        return ResponseEntity.ok(booksRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Books> createBooks(@RequestBody Books books) {

        Books book = booksRepository.save(books);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBooksById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(booksRepository.findById(id).orElse(null));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooksById(@PathVariable("id") Long id) {
        if (booksRepository.findById(id).isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        booksRepository.deleteById(id);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBooks(@PathVariable("id") Long id, @RequestBody Books books) {
        if (booksRepository.findById(id).isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(booksRepository.save(books));

    }
}
