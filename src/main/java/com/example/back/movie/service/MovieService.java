package com.example.back.movie.service;

import com.example.back.movie.dto.MovieDto;
import com.example.back.movie.dto.MovieMapper;
import com.example.back.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    public List<MovieDto> getAllMovies() {
        return movieMapper.toDtoList(movieRepository.findAll());
    }

    public List<MovieDto> getMoviesByGenre(String genre) {
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
