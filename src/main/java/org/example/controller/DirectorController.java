package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.DirectorDTO;
import org.example.entity.Director;
import org.example.entity.Film;
import org.example.service.DirectorService;
import org.example.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directors")
@AllArgsConstructor
public class DirectorController {
    private final DirectorService directorService;
    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<Director> create(@RequestBody DirectorDTO directorDTO) {
        return new ResponseEntity<>(directorService.create(directorDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Director>> readAll() {
        return new ResponseEntity<>(directorService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> readById(@PathVariable Long id) {
        return new ResponseEntity<>(directorService.readById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/films")
    public ResponseEntity<List<Film>> readByFilms(@PathVariable Long id) {
        return new ResponseEntity<>(directorService.readByFilms(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/films/{filmId}")
    public ResponseEntity<Film> readByFilmId(@PathVariable Long id, @PathVariable Long filmId) {
        return new ResponseEntity<>(directorService.readByFilmId(id, filmId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> update(@PathVariable Long id, @RequestBody Director director) {
        return new ResponseEntity<>(directorService.update(id, director), HttpStatus.OK);
    }

    @PutMapping("/{id}/films/{filmId}")
    public ResponseEntity<Director> addFilm(@PathVariable Long id, @PathVariable Long filmId) {
        Film film = filmService.setDirector(filmId, id);
        return new ResponseEntity<>(directorService.addFilm(id, filmId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        try {
            directorService.delete(id);
        }
        catch (Exception e) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.OK;
    }
}
