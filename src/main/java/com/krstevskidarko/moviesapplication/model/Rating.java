package com.krstevskidarko.moviesapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue
    private Long id;
    private Double value;
    @ManyToOne
    private Movie movie;

    public Rating() {
    }

    public Rating(Double value, Movie movie) {
        this.value = value;
        this.movie = movie;
    }
}