package com.example.back.movie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;
    @Column(name = "movie_name")
    private String movieName;
    @Column(name = "genre")
    private String genre;
    @Column(name = "age_limit")
    private Integer ageLimit;
    @Column(name = "language")
    private String language;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "run_time")
    private String runTime;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
}
