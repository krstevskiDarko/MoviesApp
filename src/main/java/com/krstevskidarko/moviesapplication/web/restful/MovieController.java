package com.krstevskidarko.moviesapplication.web.restful;


import com.krstevskidarko.moviesapplication.model.Movie;
import com.krstevskidarko.moviesapplication.model.dto.MovieDto;
import com.krstevskidarko.moviesapplication.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> findAll(){
        return this.movieService.listAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id){
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
    public ResponseEntity<Movie> rate(@PathVariable Long id, @RequestBody Double rating){

        Optional<Movie> movie = this.movieService.findById(id);

        if (movie.isPresent()){
            this.movieService.rate(id, rating);
            return ResponseEntity.ok().body(movie.get());
        }
        else {
            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<Movie> review(@PathVariable Long id, @RequestBody String review){

        Optional<Movie> movie = this.movieService.findById(id);

        if (movie.isPresent()){
            this.movieService.review(id, review);
            return ResponseEntity.ok().body(movie.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
