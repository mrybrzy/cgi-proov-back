package com.example.back.movie.dto;

public record MovieDto (Long movieId, String movieName, String genre, Integer ageLimit, String language, String startTime, String runTime, String image, String description) {
}
