package com.krstevskidarko.moviesapplication.service.impl;


import com.krstevskidarko.moviesapplication.model.Movie;
import com.krstevskidarko.moviesapplication.model.Rating;
import com.krstevskidarko.moviesapplication.model.Review;
import com.krstevskidarko.moviesapplication.model.dto.MovieDto;
import com.krstevskidarko.moviesapplication.model.exceptions.InvalidMovieIdException;
import com.krstevskidarko.moviesapplication.repository.MovieRepository;
import com.krstevskidarko.moviesapplication.service.MovieService;
import com.krstevskidarko.moviesapplication.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final RatingService ratingService;

    public MovieServiceImpl(MovieRepository movieRepository, RatingService ratingService) {
        this.movieRepository = movieRepository;
        this.ratingService = ratingService;
    }

    @Override
    public List<Movie> listAllMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return this.movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> save(MovieDto movieDto) {

        this.movieRepository.deleteByTitle(movieDto.getTitle());

        return Optional.of(this.movieRepository.save(new Movie(movieDto.getTitle(), movieDto.getDescription(), movieDto.getGenre(), movieDto.getYear())));
    }

    @Override
    public Movie create(String title, String description, String genre, Integer year) {
        return this.movieRepository.save(new Movie(title,description,genre,year));
    }

    @Override
    public Movie review(Long id, String review){
        Movie movie = this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);

        movie.getReviews().add(new Review(review,movie));

        return this.movieRepository.save(movie);
    }

    @Override
    public Movie rate(Long id, Double rating) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);

        movie.getRatings().add(new Rating(rating,movie));

        Double avgRating = this.ratingService.calculateAverageRating(movie);

        movie.setAverageRating(avgRating);

        return this.movieRepository.save(movie);
    }

    @Override
    public List<Movie> listMoviesWithGenre(String genre) {
        if(genre != null){
            return this.movieRepository.findAllByGenre(genre);
        }
        return this.movieRepository.findAll();    }

    @Override
    public List<Movie> listMoviesWithGenres(List<String> genres) {
        if(genres != null){
            return this.movieRepository.findAllByGenreIsIn(genres);
        }
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> listMovieInYear(Integer year) {
        if(year != null){
            return this.movieRepository.findAllByYear(year);
        }
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> listMoviesFromYear(Integer year) {
        if(year != null){
            return this.movieRepository.findAllByYearAfter(year);
        }
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> listMoviesToYear(Integer year) {
        if(year != null){
            return this.movieRepository.findAllByYearBefore(year);
        }
        return this.movieRepository.findAll();    }
}
