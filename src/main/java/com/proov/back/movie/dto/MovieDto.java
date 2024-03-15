package com.proov.back.movie.dto;

public record MovieDto (Long movieId, String movieName, String genre, Integer ageLimit, String language, String startTime, String runTime, Integer price, String image, String description, Integer recommendation, Float rating) {
}
