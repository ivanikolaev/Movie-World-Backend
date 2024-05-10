package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.FilmDTO;
import org.example.entity.Actor;
import org.example.entity.Director;
import org.example.entity.Film;
import org.example.entity.Review;
import org.example.service.ActorService;
import org.example.service.DirectorService;
import org.example.service.FilmService;
import org.example.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
@AllArgsConstructor
public class FilmController {
    private final FilmService filmService;
    private final DirectorService directorService;
    private final ActorService actorService;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Film> create(@RequestBody FilmDTO filmDTO) {
        return new ResponseEntity<>(filmService.create(filmDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Film>> readAll() {
        return new ResponseEntity<>(filmService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> readById(@PathVariable Long id) {
        return new ResponseEntity<>(filmService.readById(id), HttpStatus.OK);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Film>> readByYear(@PathVariable Integer year) {
        return new ResponseEntity<>(filmService.readByYear(year), HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Film>> readByGenre(@PathVariable String genre) {
        return new ResponseEntity<>(filmService.readByGenre(genre), HttpStatus.OK);
    }

    @GetMapping("/director/{directorId}/films")
    public ResponseEntity<List<Film>> readByDirectorId(@PathVariable Long directorId) {
        return new ResponseEntity<>(filmService.readByDirectorId(directorId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> update(@PathVariable Long id, @RequestBody Film film) {
        return new ResponseEntity<>(filmService.update(id, film), HttpStatus.OK);
    }

    @PutMapping("/{id}/director/{directorId}")
    public ResponseEntity<Film> setDirector(@PathVariable Long id, @PathVariable Long directorId) {
        Director director = directorService.addFilm(directorId, id);
        return new ResponseEntity<>(filmService.setDirector(id, directorId), HttpStatus.OK);
    }

    @PutMapping("/{id}/actors/{actorId}")
    public ResponseEntity<Film> addActor(@PathVariable Long id, @PathVariable Long actorId) {
        Actor actor = actorService.addFilm(actorId, id);
        return new ResponseEntity<>(filmService.addActor(id, actorId), HttpStatus.OK);
    }

    @PutMapping("/{id}/reviews/{reviewId}")
    public ResponseEntity<Film> addReview(@PathVariable Long id, @PathVariable Long reviewId) {
        Review review = reviewService.addFilm(reviewId, id);
        return new ResponseEntity<>(filmService.addReview(id, reviewId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        try {
            filmService.delete(id);
        }
        catch (Exception e) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.OK;
    }
}
