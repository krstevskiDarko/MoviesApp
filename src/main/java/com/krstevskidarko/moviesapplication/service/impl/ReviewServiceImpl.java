package com.krstevskidarko.moviesapplication.service.impl;


import com.krstevskidarko.moviesapplication.model.Movie;
import com.krstevskidarko.moviesapplication.model.Review;
import com.krstevskidarko.moviesapplication.model.dto.ReviewDto;
import com.krstevskidarko.moviesapplication.model.exceptions.InvalidMovieIdException;
import com.krstevskidarko.moviesapplication.repository.MovieRepository;
import com.krstevskidarko.moviesapplication.repository.ReviewRepository;
import com.krstevskidarko.moviesapplication.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<ReviewDto> findAll() {

        List<Review> reviews = this.reviewRepository.findAll();

        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Review save(Long movieId, String reviewText) {
        Movie movie = this.movieRepository.findById(movieId)
                .orElseThrow(InvalidMovieIdException::new);
        Review review = new Review(reviewText, movie);
        return this.reviewRepository.save(review);
    }

    private ReviewDto convertToDto(Review review){

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReview(review.getReview());
        reviewDto.setMovieId(review.getMovie().getId());

        return reviewDto;
    }
}
