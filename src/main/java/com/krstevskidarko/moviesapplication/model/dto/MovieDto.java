package com.krstevskidarko.moviesapplication.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDto {

    private String title;

    private String description;

    private String genre;

    private Integer year;

    private Double averageRating;

    private List<Long> ratings;

    public MovieDto() {
    }

    public MovieDto(String title, String description, String genre, Integer year, Double averageRating, List<Long> ratings) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.year = year;
        this.averageRating = averageRating;
        this.ratings = ratings;
    }
}
