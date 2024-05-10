package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.ActorDTO;
import org.example.entity.Actor;
import org.example.entity.Film;
import org.example.repository.ActorRepository;
import org.example.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;
    private final FilmRepository filmRepository;

    public Actor create(ActorDTO dto) {
        return actorRepository.save(Actor.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .country(dto.getCountry())
                .build());
    }

    public List<Actor> readAll() {
        return actorRepository.findAll();
    }

    public Actor update(Long id, Actor actor) {
        Actor oldActor = actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found" + id));
        oldActor.setFirstName(actor.getFirstName());
        oldActor.setLastName(actor.getLastName());
        oldActor.setCountry(actor.getCountry());
        return actorRepository.save(oldActor);
    }

    public void delete(Long id) {
        actorRepository.deleteById(id);
    }

    public Actor readById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found" + id));
    }

    public List<Film> readByFilms(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found" + id));
        return actor.getFilms();
    }

    public Film readByFilmId(Long id, Long filmId) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor not found" + id));
        return actor.getFilms().stream()
                .filter(film -> film.getId().equals(filmId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Film not found" + filmId));
    }

    public Actor addFilm(Long actorId, Long filmId) {
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new RuntimeException("Actor not found" + actorId));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found" + filmId));
        actor.getFilms().add(film);
        return actorRepository.save(actor);
    }
}
