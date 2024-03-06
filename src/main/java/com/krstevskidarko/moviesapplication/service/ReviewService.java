package com.krstevskidarko.moviesapplication.service;

import com.krstevskidarko.moviesapplication.model.Review;
import com.krstevskidarko.moviesapplication.model.dto.ReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDto> findAll();

    Optional<ReviewDto> findById(Long id);

    List<ReviewDto> findByIdIn(List<Long> ids);

    Review save(Long id, String text);
}
