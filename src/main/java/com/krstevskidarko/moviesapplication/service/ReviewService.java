package com.krstevskidarko.moviesapplication.service;

import com.krstevskidarko.moviesapplication.model.Review;
import com.krstevskidarko.moviesapplication.model.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> findAll();

    Review save(Long id, String text);
}
