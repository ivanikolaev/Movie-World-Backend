package org.example.repository;

import org.example.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий представляет собой интерфейс, который предоставляет набор методов для взаимодействия с базой данных.
 * <p>
 * JpaRepository - это интерфейс Spring Data JPA, который предоставляет множество методов (Create, Read, Update, Delete)
 * для работы с сущностями JPA без необходимости написания какого-либо кода реализации.
 */
@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByYear(int year);
    List<Film> findByGenre(String genre);
    List<Film> findByDirectorId(Long directorId);

}