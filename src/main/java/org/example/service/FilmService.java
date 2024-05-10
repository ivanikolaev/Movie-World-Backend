package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.FilmDTO;
import org.example.entity.Actor;
import org.example.entity.Director;
import org.example.entity.Film;
import org.example.entity.Review;
import org.example.repository.ActorRepository;
import org.example.repository.DirectorRepository;
import org.example.repository.FilmRepository;
import org.example.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service для выполнения CRUD операций для сущности "Фильм".
 */
@Service
@AllArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final ReviewRepository reviewRepository;

    /**
     * CREATE
     * <p>
     * Создает новый объект Film на основе предоставленного FilmDTO и сохраняет его в репозиторий фильмов.
     *
     * @param  dto  объект FilmDTO, содержащий информацию о фильме
     * @return      созданный объект класса "Фильм"
     */
    public Film create(FilmDTO dto) {
        return filmRepository.save(Film.builder()
                .name(dto.getName())
                .year(dto.getYear())
                .rating(dto.getRating())
                .genre(dto.getGenre())
                .build());
    }

    /**
     * READ
     * <p>
     * Считывает все фильмы из хранилища фильмов.
     *
     * @return список объектов Film, представляющих все фильмы в репозитории
     */
    public List<Film> readAll(){
        return filmRepository.findAll();
    }

    /**
     * UPDATE
     * <p>
     * Обновляет фильм в репозитории.
     *
     * @param  film  объект класса "Фильм", который необходимо обновить
     * @return       обновленный объект класса "Фильм"
     */
    public Film update(Long id, Film film) {
        Film oldFilm = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found" + id));
        oldFilm.setName(film.getName());
        oldFilm.setYear(film.getYear());
        oldFilm.setRating(film.getRating());
        oldFilm.setGenre(film.getGenre());
        return filmRepository.save(oldFilm);
    }

    /**
     * DELETE
     * <p>
     * Удаляет фильм с указанным идентификатором.
     *
     * @param  id  ID фильма, который нужно удалить
     */
    public void delete(Long id) {

        filmRepository.deleteById(id);
    }

    /**
     * READ BY ID
     * <p>
     * Считывает фильм по указанному идентификатору.
     *
     * @param  id  ID фильма, который нужно считать
     * @return     объект класса "Фильм" с указанным идентификатором
     * @throws     RuntimeException если фильм с указанным ID не найден
     */
    public Film readById(Long id) {
        return filmRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Film not found - " + id));
    }

    /**
     * Добавляет режиссера к фильму.
     *
     * @param filmId     ID фильма
     * @param directorId ID режиссера
     * @return обновленный объект фильма
     */
    public Film setDirector(Long filmId, Long directorId) {
        Film film = readById(filmId);
        Director director = directorRepository.findById(directorId).orElseThrow(() ->
                new RuntimeException("Director not found - " + directorId));
        film.setDirector(director);
        return filmRepository.save(film);
    }

    /**
     * Добавляет актера к фильму.
     *
     * @param filmId  ID фильма
     * @param actorId ID актера
     * @return обновленный объект фильма
     */
    public Film addActor(Long filmId, Long actorId) {
        Film film = readById(filmId);
        Actor actor = actorRepository.findById(actorId).orElseThrow(() ->
                new RuntimeException("Actor not found - " + actorId));
        film.getActors().add(actor);
        return filmRepository.save(film);
    }

    /**
     * Добавляет обзор к фильму.
     *
     * @param filmId   ID фильма
     * @param reviewId ID обзора
     * @return обновленный объект фильма
     */
    public Film addReview(Long filmId, Long reviewId) {
        Film film = readById(filmId);
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new RuntimeException("Review not found - " + reviewId));
        film.getReviews().add(review);
        return filmRepository.save(film);
    }


    public List<Film> readByYear(int year) {
        return filmRepository.findByYear(year);
    }
    public List<Film> readByGenre(String genre) {
        return filmRepository.findByGenre(genre);
    }

    public List<Film> readByDirectorId(Long directorId) {
        return filmRepository.findByDirectorId(directorId);
    }
}
