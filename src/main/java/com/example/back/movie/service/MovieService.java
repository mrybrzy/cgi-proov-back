package com.example.back.movie.service;

import com.example.back.movie.dto.MovieDto;
import com.example.back.movie.dto.MovieMapper;
import com.example.back.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    public List<MovieDto> getAllMovies() {
        return movieMapper.toDtoList(movieRepository.findAll());
    }

    public List<MovieDto> getMoviesFiltered(String genre, Integer age, String start, String language) {
        List<MovieDto> genreMatch = getMoviesByGenre(genre);
        List<MovieDto> ageMatch = getMoviesByAge(age);
        List<MovieDto> timeMatch = getMoviesByTime(start);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);
        List<MovieDto> combinedList = new ArrayList<>();
        combinedList.addAll(genreMatch);
        combinedList.addAll(ageMatch);
        combinedList.addAll(timeMatch);
        combinedList.addAll(languageMatch);

        Set<MovieDto> matches = new HashSet<>(combinedList);
        return new ArrayList<>(matches);




    }

    private List<MovieDto> getMoviesByLanguage(String language) {
        List<MovieDto> movieDtoList = getAllMovies();
        List<MovieDto> matches = new ArrayList<>();
        for (MovieDto movieDto : movieDtoList) {
            if (movieDto.language().equalsIgnoreCase(language)) {
                matches.add(movieDto);
            }
        }
        return matches;
    }

    private List<MovieDto> getMoviesByTime(String start) {
        List<MovieDto> movieDtoList = getAllMovies();
        List<MovieDto> matches = new ArrayList<>();
        for (MovieDto movieDto : movieDtoList) {
            if (Integer.parseInt(movieDto.startTime()) >= Integer.parseInt(start)) {
                matches.add(movieDto);
            }
        }
        return matches;
    }

    private List<MovieDto> getMoviesByAge(Integer age) {
        List<MovieDto> movieDtoList = getAllMovies();
        List<MovieDto> matches = new ArrayList<>();
        for (MovieDto movieDto : movieDtoList) {
            if (movieDto.ageLimit() <= age) {
                matches.add(movieDto);
            }
        }
        return matches;
    }

    private List<MovieDto> getMoviesByGenre(String genre) {
        List<MovieDto> movieDtoList = getAllMovies();
        List<MovieDto> matches = new ArrayList<>();
        for (MovieDto movieDto : movieDtoList) {
            if (movieDto.genre().toLowerCase().contains(genre)) {
                matches.add(movieDto);
            }
        }
        return matches;
    }
}
