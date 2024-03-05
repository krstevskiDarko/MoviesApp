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
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final RatingService ratingService;

    public MovieServiceImpl(MovieRepository movieRepository, RatingService ratingService) {
        this.movieRepository = movieRepository;
        this.ratingService = ratingService;
    }

    @Override
    public List<MovieDto> listAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDto> findById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        return movieOptional.map(this::convertToDto);
    }

    public Optional<Movie> findMovieById(Long id) {
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



//    @Override
//    public Movie review(Long id, String review){
//        Movie movie = this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
//
//        movie.getReviews().add(new Review(review,movie));
//
//        return this.movieRepository.save(movie);
//    }

    @Override
    public Movie rate(Long id, Double rating) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);

        movie.getRatings().add(new Rating(rating,movie));

        Double avgRating = this.ratingService.calculateAverageRating(movie);
        movie.setAverageRating(avgRating);

        return this.movieRepository.save(movie);
    }

    @Override
    public List<MovieDto> listMoviesWithGenre(String genre) {
        if(genre != null){
            List<Movie> movies = this.movieRepository.findAllByGenre(genre);
            return movies.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

        }
        List<Movie> movies = this.movieRepository.findAll();
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> listMoviesWithGenres(List<String> genres) {
        if(genres != null){
            List<Movie> movies = this.movieRepository.findAllByGenreIsIn(genres);
            return movies.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
        List<Movie> movies = this.movieRepository.findAll();
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> listMovieInYear(Integer year) {
        if(year != null){
            List<Movie> movies =this.movieRepository.findAllByYear(year);
            return movies.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
        List<Movie> movies = this.movieRepository.findAll();
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> listMoviesFromYear(Integer year) {
        if(year != null){
            List<Movie> movies = this.movieRepository.findAllByYearAfter(year);
            return movies.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
        List<Movie> movies = this.movieRepository.findAll();
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> listMoviesToYear(Integer year) {
        if (year != null) {
            List<Movie> movies = this.movieRepository.findAllByYearBefore(year);
            return movies.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

        }
        List<Movie> movies = this.movieRepository.findAll();
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MovieDto convertToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        movieDto.setGenre(movie.getGenre());
        movieDto.setYear(movie.getYear());
        movieDto.setAverageRating(movie.getAverageRating());
        List<Long> ratingIds = movie.getRatings().stream()
                .map(Rating::getId)
                .collect(Collectors.toList());
        movieDto.setRatingIds(ratingIds);

        List<Long> reviewIds = movie.getReviews().stream()
                .map(Review::getId)
                .collect(Collectors.toList());
        movieDto.setReviewIds(reviewIds);
        return movieDto;
    }
}
