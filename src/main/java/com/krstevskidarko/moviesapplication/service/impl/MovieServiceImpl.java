package com.krstevskidarko.moviesapplication.service.impl;


import com.krstevskidarko.moviesapplication.model.Movie;
import com.krstevskidarko.moviesapplication.model.Rating;
import com.krstevskidarko.moviesapplication.model.Review;
import com.krstevskidarko.moviesapplication.model.dto.MovieDto;
import com.krstevskidarko.moviesapplication.model.exceptions.InvalidMovieIdException;
import com.krstevskidarko.moviesapplication.repository.MovieRepository;
import com.krstevskidarko.moviesapplication.service.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(this::convertToDto);
    }

    public Optional<Movie> findMovieById(Long id) {
        return this.movieRepository.findById(id);
    }

    @Transactional
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

    @Transactional
    @Override
    public Movie rate(Long id, Double rating) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);

        Double avgRating = calculateAverageRating(movie);

        movie.setAverageRating(avgRating);

        return this.movieRepository.save(movie);
    }

    private Double calculateAverageRating(Movie movie){
        List<Rating> ratings = movie.getRatings();
        if (ratings.isEmpty()) {
            return 0.0;
        }

        Double sum = 0.0;
        int count = 0;
        for (Rating rating : ratings) {
            if (rating.getValue() != null) {
                sum += rating.getValue();
                count++;
            }
        }
        if (count == 0) {
            return 0.0;
        }
        System.out.println(sum);
        System.out.println(count);
        return sum / count;
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

    @Override
    public List<MovieDto> listMoviesWithTitle(String title) {
        if(title!=null){
            List<Movie> movies = this.movieRepository.findAllByTitleLike(title);
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
    public Page<MovieDto> listMoviesWithPagination(Pageable pageable) {

        Page<Movie> movies = this.movieRepository.findAll(pageable);

        return  movies.map(this::convertToDto);

    }

    private MovieDto convertToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();

        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        movieDto.setGenre(movie.getGenre());
        movieDto.setYear(movie.getYear());
        movieDto.setAverageRating(movie.getAverageRating());

        movieDto.setRatings(movie.getRatings().stream()
                .map(Rating::getValue)
                .collect(Collectors.toList()));

        movieDto.setReviews(movie.getReviews().stream()
                .map(Review::getReview)
                .collect(Collectors.toList()));

        return movieDto;
    }
}
