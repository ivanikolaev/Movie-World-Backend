package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.DirectorDTO;
import org.example.entity.Director;
import org.example.entity.Film;
import org.example.repository.DirectorRepository;
import org.example.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;

    public Director create(DirectorDTO dto) {
        return directorRepository.save(Director.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .country(dto.getCountry())
                .build());
    }

    public List<Director> readAll() {
        return directorRepository.findAll();
    }

    public Director update(Long id, Director director) {
        Director oldDirector = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found" + id));
        oldDirector.setFirstName(director.getFirstName());
        oldDirector.setLastName(director.getLastName());
        oldDirector.setCountry(director.getCountry());
        return directorRepository.save(oldDirector);
    }

    public void delete(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found" + id));
        director.setFilms(null);
        directorRepository.save(director);
        directorRepository.deleteById(id);
    }

    public Director readById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found" + id));
    }

    public List<Film> readByFilms(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found" + id));
        return director.getFilms();
    }

    public Film readByFilmId(Long id, Long filmId) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found" + id));
        return director.getFilms().stream()
                .filter(film -> film.getId().equals(filmId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Film not found" + filmId));
    }

    public Director addFilm(Long directorId, Long filmId) {
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new RuntimeException("Director not found" + directorId));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found" + filmId));
        director.getFilms().add(film);
        return directorRepository.save(director);
    }
}
