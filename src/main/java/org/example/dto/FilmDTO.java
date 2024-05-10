package org.example.dto;

import lombok.Data;

/**
 * Объект DTO (Data Transfer Object) представляет собой удобный способ организации
 * взаимодействия между клиентом и сервером, так как он позволяет отправлять только необходимые данные.
 */
@Data
public class FilmDTO {
    private String name;
    private int year;
    private double rating;
    private String genre;
}