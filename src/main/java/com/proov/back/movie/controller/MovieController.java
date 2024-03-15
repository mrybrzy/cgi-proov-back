package com.proov.back.movie.controller;

import com.proov.back.movie.dto.MovieDto;
import com.proov.back.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/home")
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/{movie_id}")
    public MovieDto getMovieById(@PathVariable("movie_id") Long movieId) {
        return movieService.getMovieById(movieId);
    }

    @GetMapping("/search")
    public List<MovieDto> getMoviesAll() {
        return movieService.getAllMovies();
    }

    @GetMapping("/search/language/{language}")
    public List<MovieDto> getMoviesByLanguage(@PathVariable("language") String language) {
        return movieService.getMoviesByLanguage(language);
    }

    @GetMapping("/search/time/{start}")
    public List<MovieDto> getMoviesByTime(@PathVariable("start") Integer start) {
        return movieService.getMoviesByTime(start);
    }

    @GetMapping("/search/time/{start}/language/{language}")
    public List<MovieDto> getMoviesByTimeAndLanguage(@PathVariable("start") Integer start,
                                                     @PathVariable("language") String language) {
        return movieService.getMoviesByTimeAndLanguage(start, language);
    }

    @GetMapping("/search/age/{age}")
    public List<MovieDto> getMoviesByAge(@PathVariable("age") Integer age){
        return movieService.getMoviesByAge(age);
    }

    @GetMapping("/search/age/{age}/language/{language}")
    public List<MovieDto> getMoviesByAgeAndLanguage(@PathVariable("age") Integer age,
                                           @PathVariable("language") String language) {
        return movieService.getMoviesByAgeAndLanguage(age, language);
    }

    @GetMapping("/search/age/{age}/time/{start}")
    public List<MovieDto> getMoviesByAgeAndStart(@PathVariable("age") Integer age,
                                           @PathVariable("start") Integer start) {
        return movieService.getMoviesByAgeAndStart(age, start);
    }

    @GetMapping("/search/age/{age}/time/{start}/language/{language}")
    public List<MovieDto> getMoviesByAgeStartLanguage(@PathVariable("age") Integer age,
                                           @PathVariable("start") Integer start,
                                           @PathVariable("language") String language) {
        return movieService.getMoviesByAgeStartLanguage(age, start, language);
    }

    @GetMapping("/search/genre/{genre}")
    public List<MovieDto> getMoviesByGenre(@PathVariable("genre") String genre) {
        return movieService.getMoviesByGenre(genre);
    }

    @GetMapping("/search/genre/{genre}/language/{language}")
    public List<MovieDto> getMoviesByGenreLanguage(@PathVariable("genre") String genre,
                                           @PathVariable("language") String language) {
        return movieService.getMoviesByGenreLanguage(genre,language);
    }

    @GetMapping("/search/genre/{genre}/time/{start}")
    public List<MovieDto> getMoviesByGenreStart(@PathVariable("genre") String genre,
                                           @PathVariable("start") Integer start) {
        return movieService.getMoviesByGenreStart(genre, start);
    }

    @GetMapping("/search/genre/{genre}/time/{start}/language/{language}")
    public List<MovieDto> getMoviesByGenreStartLanguage(@PathVariable("genre") String genre,
                                           @PathVariable("start") Integer start,
                                           @PathVariable("language") String language) {
        return movieService.getMoviesByGenreStartLanguage(genre, start, language);
    }

    @GetMapping("/search/genre/{genre}/age/{age}")
    public List<MovieDto> getMoviesByGenreAge(@PathVariable("genre") String genre,
                                           @PathVariable("age") Integer age) {
        return movieService.getMoviesByGenreAge(genre, age);
    }

    @GetMapping("/search/genre/{genre}/age/{age}/language/{language}")
    public List<MovieDto> getMoviesByGenreAgeLanguage(@PathVariable("genre") String genre,
                                           @PathVariable("age") Integer age,
                                           @PathVariable("language") String language) {
        return movieService.getMoviesByGenreAgeLanguage(genre, age, language);
    }

    @GetMapping("/search/genre/{genre}/age/{age}/time/{start}")
    public List<MovieDto> getMoviesByGenreAgeStart(@PathVariable("genre") String genre,
                                           @PathVariable("age") Integer age,
                                           @PathVariable("start") Integer start) {
        return movieService.getMoviesByGenreAgeStart(genre, age, start);
    }

    @GetMapping("/search/genre/{genre}/age/{age}/time/{start}/language/{language}")
    public List<MovieDto> getMoviesFiltered(@PathVariable("genre") String genre,
                                           @PathVariable("age") Integer age,
                                           @PathVariable("start") Integer start,
                                           @PathVariable("language") String language) {
        return movieService.getMoviesFiltered(genre, age, start, language);
    }
    @GetMapping("/recommendation")
    public void removeRecommendation() {
        movieService.removeRecommendation();
    }
}
