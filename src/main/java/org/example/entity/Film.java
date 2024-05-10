package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity // Сущность
@Data // Генерирует геттеры и сеттеры
@Builder // Паттерн проектирования Builder
@NoArgsConstructor // Генерирует конструктор без аргументов
@AllArgsConstructor // Генерирует конструктор с аргументами
public class Film {
    @Id // Первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоинкремент
    private Long id;

    private String name;
    private int year;
    private double rating;
    private String genre;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @JsonIgnoreProperties("films")
    private Director director;

    @ManyToMany
    @JsonIgnoreProperties("films")
    private List<Actor> actors;

    @OneToMany
    @JsonIgnoreProperties("film")
    private List<Review> reviews;
}