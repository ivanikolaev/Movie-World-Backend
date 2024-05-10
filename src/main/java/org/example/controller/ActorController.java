package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.ActorDTO;
import org.example.entity.Actor;
import org.example.entity.Film;
import org.example.service.ActorService;
import org.example.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
@AllArgsConstructor
public class ActorController {
    private final ActorService actorService;
    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<Actor> create(@RequestBody ActorDTO actorDTO) {
        return new ResponseEntity<>(actorService.create(actorDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Actor>> readAll() {
        return new ResponseEntity<>(actorService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> readById(@PathVariable Long id) {
        return new ResponseEntity<>(actorService.readById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/films")
    public ResponseEntity<List<Film>> readByFilms(@PathVariable Long id) {
        return new ResponseEntity<>(actorService.readByFilms(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/films/{filmId}")
    public ResponseEntity<Film> readByFilmId(@PathVariable Long id, @PathVariable Long filmId) {
        return new ResponseEntity<>(actorService.readByFilmId(id, filmId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> update(@PathVariable Long id, @RequestBody Actor actor) {
        return new ResponseEntity<>(actorService.update(id, actor), HttpStatus.OK);
    }

    @PutMapping("/{id}/films/{filmId}")
    public ResponseEntity<Actor> addFilm(@PathVariable Long id, @PathVariable Long filmId) {
        Film film = filmService.addActor(filmId, id);
        return new ResponseEntity<>(actorService.addFilm(id, filmId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        try {
            actorService.delete(id);
        }
        catch (Exception e) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.OK;
    }
}
