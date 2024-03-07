package com.krstevskidarko.moviesapplication.repository;

import com.krstevskidarko.moviesapplication.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByGenreIsIn(List<String> genre);

    List<Movie> findAllByGenre(String genre);
    List<Movie> findAllByYear(Integer year);

    List<Movie> findAllByYearAfter(Integer year);

    List<Movie> findAllByYearBefore(Integer year);

    List<Movie> findAllByTitleLike(String title);

    void deleteByTitle(String title);
    Page<Movie> findAll(Pageable pageable);

}
