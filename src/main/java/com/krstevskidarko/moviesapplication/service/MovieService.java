package com.krstevskidarko.moviesapplication.service;

import com.krstevskidarko.moviesapplication.model.Movie;
import com.krstevskidarko.moviesapplication.model.dto.MovieDto;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<MovieDto> listAllMovies();

    Optional<MovieDto> findById(Long id);

    Optional<Movie> save(MovieDto movieDto);

//    Movie review(Long id, String review);

    Optional<Movie> findMovieById(Long id);

    Movie create(String title, String description, String genre, Integer year);

    Movie rate(Long id, Double rating);

    List<MovieDto> listMoviesWithGenre(String genre);

    List<MovieDto> listMoviesWithGenres(List<String> genres);

    List<MovieDto> listMovieInYear(Integer year);

    List<MovieDto> listMoviesFromYear(Integer year);

    List<MovieDto> listMoviesToYear(Integer year);
}
