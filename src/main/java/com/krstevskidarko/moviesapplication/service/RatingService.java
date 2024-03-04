package com.krstevskidarko.moviesapplication.service;


import com.krstevskidarko.moviesapplication.model.Movie;

public interface RatingService {
    Double calculateAverageRating(Movie movie);
}
