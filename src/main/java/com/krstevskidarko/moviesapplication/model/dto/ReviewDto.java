package com.krstevskidarko.moviesapplication.model.dto;

import lombok.Data;

@Data
public class ReviewDto {

    private String review;

    private Long movieId;

    private String movieTitle;

    private String movieDescription;

    private Double movieRating;


    public ReviewDto() {
    }

    public ReviewDto( String review) {
        this.review = review;
    }
}