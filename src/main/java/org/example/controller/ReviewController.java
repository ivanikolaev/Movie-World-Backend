package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.ReviewDTO;
import org.example.entity.Film;
import org.example.entity.Review;
import org.example.service.FilmService;
import org.example.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<Review> create(@RequestBody ReviewDTO reviewDTO) {
        return new ResponseEntity<>(reviewService.create(reviewDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> readAll() {
        return new ResponseEntity<>(reviewService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> readById(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.readById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> update(@PathVariable Long id, @RequestBody Review review) {
        return new ResponseEntity<>(reviewService.update(id, review), HttpStatus.OK);
    }

    @PutMapping("/{id}/film/{filmId}")
    public ResponseEntity<Review> setFilm(@PathVariable Long id, @PathVariable Long filmId) {
        Film film = filmService.addReview(filmId, id);
        return new ResponseEntity<>(reviewService.addFilm(id, filmId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        try {
            reviewService.delete(id);
        }
        catch (Exception e) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.OK;
    }
}
