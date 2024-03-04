package com.krstevskidarko.moviesapplication.repository;

import com.krstevskidarko.moviesapplication.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
