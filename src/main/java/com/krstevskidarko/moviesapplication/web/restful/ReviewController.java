package com.krstevskidarko.moviesapplication.web.restful;


import com.krstevskidarko.moviesapplication.model.Review;
import com.krstevskidarko.moviesapplication.model.dto.MovieDto;
import com.krstevskidarko.moviesapplication.model.dto.ReviewDto;
import com.krstevskidarko.moviesapplication.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDto> findAll(){
        return this.reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> findById(@PathVariable Long id){
        return this.reviewService.findById(id)
                .map(review -> ResponseEntity.ok().body(review))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //todo Implement logic with ReviewDto and RatingDto
}
