package com.krstevskidarko.moviesapplication.web.restful;


import com.krstevskidarko.moviesapplication.model.Review;
import com.krstevskidarko.moviesapplication.model.dto.ReviewDto;
import com.krstevskidarko.moviesapplication.service.ReviewService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
