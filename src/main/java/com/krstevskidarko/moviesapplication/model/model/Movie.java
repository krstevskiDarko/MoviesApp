package com.krstevskidarko.moviesapplication.model.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private String genre;

    private Integer year;

    private Double averageRating;

    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews = new ArrayList<>();

    public Movie() {
    }

    public Movie(String title, String description, String genre, Integer year) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.year = year;
    }

}
