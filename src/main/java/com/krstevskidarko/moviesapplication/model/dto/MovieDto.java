package com.krstevskidarko.moviesapplication.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDto {

    private Long id;

    private String title;

    private String description;

    private String genre;

    private Integer year;

    private Double averageRating;

    private List<Long> ratingIds;

    private List<Long> reviewIds;

    public MovieDto() {
    }
//
//    public MovieDto(Long id, String title, String description, String genre, Integer year, Double averageRating, List<Long> ratingIds, List<Long> reviewIds) {
//        this.title = title;
//        this.description = description;
//        this.genre = genre;
//        this.year = year;
//        this.averageRating = averageRating;
//        this.ratingIds = ratingIds;
//        this.reviewIds = reviewIds;
//        this.id = id;
//    }
}
