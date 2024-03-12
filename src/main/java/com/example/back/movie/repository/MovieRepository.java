package com.example.back.movie.repository;

import com.example.back.movie.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    Page<MovieEntity> findAllOrderByRecommendation(Integer age, Pageable pageable);
}
