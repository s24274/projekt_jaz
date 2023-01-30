package org.example.controllers;


import org.example.model.Theme;
import org.example.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/theme/")
public class ThemeController {  ThemeRepository themeRepository;

    @Autowired
    public ThemeController(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Theme>> getAllTheme() {
        return ResponseEntity.ok(themeRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Theme> createTheme(@RequestBody Theme theme) {

        Theme theme1 = themeRepository.save(theme);
        return ResponseEntity.ok(theme1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theme> getThemeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(themeRepository.findById(id).orElse(null));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteThemeById(@PathVariable("id") Long id) {
        if (themeRepository.findById(id).isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        themeRepository.deleteById(id);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theme> updateTheme(@PathVariable("id") Long id, @RequestBody Theme theme) {
        if (themeRepository.findById(id).isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(themeRepository.save(theme));

    }
}
