package com.krstevskidarko.moviesapplication.service;


import com.krstevskidarko.moviesapplication.model.Movie;
import com.krstevskidarko.moviesapplication.model.Rating;

public interface RatingService {

    Rating save(Long id, Double rating);
    Double calculateAverageRating(Movie movie);
}
