package com.example.back.movie.controller;

import com.example.back.movie.dto.MovieDto;
import com.example.back.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/home")
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/search/{genre}/{age}/{start}/{language}")
    public List<MovieDto> getMoviesByGenre(@PathVariable("genre") String genre,
                                           @PathVariable("age") Integer age,
                                           @PathVariable("start") String start,
                                           @PathVariable("language") String language) {
        return movieService.getMoviesFiltered(genre, age, start, language);
    }
    @GetMapping("/movie/{movie_id}")
    public MovieDto getMovieById(@PathVariable("movie_id") Long movieId) {
        return movieService.getMovieById(movieId);
    }
}
