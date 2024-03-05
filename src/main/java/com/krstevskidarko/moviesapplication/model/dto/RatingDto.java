package com.krstevskidarko.moviesapplication.model.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class RatingDto {

    private Long id;

    private Double value;

    public RatingDto() {
    }

    public RatingDto(Long id, Double value) {
        this.id = id;
        this.value = value;
    }
}