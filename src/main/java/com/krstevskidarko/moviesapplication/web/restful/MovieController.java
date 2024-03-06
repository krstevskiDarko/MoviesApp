package com.krstevskidarko.moviesapplication.web.restful;


import com.krstevskidarko.moviesapplication.model.Movie;
import com.krstevskidarko.moviesapplication.model.dto.MovieDto;
import com.krstevskidarko.moviesapplication.service.MovieService;
import com.krstevskidarko.moviesapplication.service.RatingService;
import com.krstevskidarko.moviesapplication.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    private final ReviewService reviewService;

    private final RatingService ratingService;


    public MovieController(MovieService movieService, ReviewService reviewService, RatingService ratingService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<MovieDto> findAll(){
        return this.movieService.listAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> findById(@PathVariable Long id){
        return this.movieService.findById(id)
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody MovieDto movieDto){
        return this.movieService.save(movieDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<Double> rate(@PathVariable Long id, @RequestBody Double rating){

        Optional<Movie> movie = this.movieService.findMovieById(id);

        if (movie.isPresent() && rating>0 && rating <= 10){
            this.ratingService.save(id,rating);

            this.movieService.rate(id,rating);
            return ResponseEntity.ok().body(0.0);
        }
            return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<String> review(@PathVariable Long id, @RequestBody String review){


        Optional<MovieDto> movie = this.movieService.findById(id);

        if (movie.isPresent()) {
            this.reviewService.save(id,review);
            return ResponseEntity.ok().body("The review you added was successful!");
        }
        return ResponseEntity.badRequest().build();

    }


}
