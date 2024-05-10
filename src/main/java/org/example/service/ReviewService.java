package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.ReviewDTO;
import org.example.entity.Film;
import org.example.entity.Review;
import org.example.repository.FilmRepository;
import org.example.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final FilmRepository filmRepository;

    public Review create(ReviewDTO dto) {
        return reviewRepository.save(Review.builder()
                .authorName(dto.getAuthorName())
                .text(dto.getText())
                .rating(dto.getRating())
                .build());
    }

    public List<Review> readAll() {
        return reviewRepository.findAll();
    }

    public Review update(Long id, Review review) {
        Review oldReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found" + id));
        oldReview.setAuthorName(review.getAuthorName());
        oldReview.setText(review.getText());
        oldReview.setRating(review.getRating());
        return reviewRepository.save(oldReview);
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    public Review readById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found" + id));
    }

    public Review addFilm(Long reviewId, Long filmId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found" + reviewId));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found" + filmId));
        review.setFilm(film);
        return reviewRepository.save(review);
    }
}
