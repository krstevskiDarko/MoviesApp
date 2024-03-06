package com.krstevskidarko.moviesapplication.service.impl;


import com.krstevskidarko.moviesapplication.model.Movie;
import com.krstevskidarko.moviesapplication.model.Rating;
import com.krstevskidarko.moviesapplication.model.exceptions.InvalidMovieIdException;
import com.krstevskidarko.moviesapplication.repository.MovieRepository;
import com.krstevskidarko.moviesapplication.repository.RatingRepository;
import com.krstevskidarko.moviesapplication.service.RatingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private final MovieRepository movieRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    @Override
    public Rating save(Long id, Double value) {
        Movie movie = this.movieRepository.findById(id)
                .orElseThrow(InvalidMovieIdException::new);

        Rating rating = new Rating(value,movie);

        return this.ratingRepository.save(rating);
    }
}
