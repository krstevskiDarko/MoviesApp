package com.krstevskidarko.moviesapplication.repository;

import com.krstevskidarko.moviesapplication.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("SELECT m FROM Movie m WHERE " +
            "(:title IS NULL OR m.title = :title) AND " +
            "(:genre IS NULL OR m.genre = :genre) AND " +
            "(:genres IS NULL OR m.genre IN :genres) AND " +
            "(:year IS NULL OR m.year = :year) AND " +
            "(:yearFrom IS NULL OR m.year >= :yearFrom) AND " +
            "(:yearTo IS NULL OR m.year <= :yearTo)")
    Page<Movie> findAllWithFilters(String title, String genre, List<String> genres, Integer year, Integer yearFrom, Integer yearTo,Pageable pageable);

}
