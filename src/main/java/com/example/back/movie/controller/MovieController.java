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

    @GetMapping("/search/{genre}")
    public List<MovieDto> getMoviesByGenre(@PathVariable("genre") String genre) {
        return movieService.getMoviesByGenre(genre);
    }
}
