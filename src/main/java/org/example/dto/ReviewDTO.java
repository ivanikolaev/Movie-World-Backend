package org.example.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private String authorName;
    private String text;
    private double rating;
}