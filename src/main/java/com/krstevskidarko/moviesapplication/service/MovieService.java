package com.krstevskidarko.moviesapplication.service;

import com.krstevskidarko.moviesapplication.model.Movie;
import com.krstevskidarko.moviesapplication.model.dto.MovieDto;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> listAllMovies();

    Optional<Movie> findById(Long id);

    Optional<Movie> save(MovieDto movieDto);

    Movie review(Long id, String review);

    Movie create(String title, String description, String genre, Integer year);

    Movie rate(Long id, Double rating);

    List<Movie> listMoviesWithGenre(String genre);

    List<Movie> listMoviesWithGenres(List<String> genres);

    List<Movie> listMovieInYear(Integer year);

    List<Movie> listMoviesFromYear(Integer year);

    List<Movie> listMoviesToYear(Integer year);
}
