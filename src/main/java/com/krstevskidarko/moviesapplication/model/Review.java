package com.krstevskidarko.moviesapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private String review;

    @ManyToOne
    private Movie movie;

    public Review() {
    }

    public Review(String review, Movie movie) {
        this.review = review;
        this.movie = movie;
    }
}
