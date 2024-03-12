package com.example.back.movie.service;

import com.example.back.movie.dto.MovieDto;
import com.example.back.movie.dto.MovieMapper;
import com.example.back.movie.entity.MovieEntity;
import com.example.back.movie.repository.MovieRepository;
import com.example.back.seat.dto.SeatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<MovieDto> getAllMovies() {
        List<MovieDto> all = movieMapper.toDtoList(movieRepository.findAll());
        all.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return all;
    }

    public List<MovieDto> getMoviesFiltered(String genre, Integer age, Integer start, String language) {
        List<MovieDto> genreMatch = getMoviesByGenre(genre);
        List<MovieDto> ageMatch = getMoviesByAge(age);
        List<MovieDto> timeMatch = getMoviesByTime(start);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);

        List<MovieDto> matches = new ArrayList<>();

        for (MovieDto movieDto : languageMatch) {
            if (genreMatch.contains(movieDto) && ageMatch.contains(movieDto) && timeMatch.contains(movieDto)) {
                matches.add(movieDto);
            }
        }
        matches.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return matches;

    }

    public List<MovieDto> getMoviesByLanguage(String language) {
        List<MovieDto> movieDtoList = getAllMovies();
        List<MovieDto> matches = new ArrayList<>();
        for (MovieDto movieDto : movieDtoList) {
            if (movieDto.language().equalsIgnoreCase(language)) {
                matches.add(movieDto);
            }
        }
        matches.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return matches;
    }

    public List<MovieDto> getMoviesByTime(Integer start) {
        Integer next = 0;
        if (start == 9) {
            next = 12;
        } else if (start == 12) {
            next = 18;
        }
        List<MovieDto> movieDtoList = getAllMovies();
        List<MovieDto> matches = new ArrayList<>();
        for (MovieDto movieDto : movieDtoList) {
            int time = Integer.parseInt(movieDto.startTime().substring(0, 2));
            if (time >= start && time < next) {
                matches.add(movieDto);
            }
        }
        matches.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return matches;
    }

    public List<MovieDto> getMoviesByAge(Integer age) {
        List<MovieDto> movieDtoList = getAllMovies();
        List<MovieDto> matches = new ArrayList<>();
        for (MovieDto movieDto : movieDtoList) {
            if (movieDto.ageLimit() <= age) {
                matches.add(movieDto);
            }
        }
        matches.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return matches;
    }

    public List<MovieDto> getMoviesByGenre(String genre) {
        String[] genres = genre.split(",");
        List<MovieDto> movieDtoList = getAllMovies();
        List<MovieDto> matches = new ArrayList<>();

        for (MovieDto movieDto : movieDtoList) {
            String movieGenres = movieDto.genre().toLowerCase();
            boolean allGenresMatch = true;

            for (String individualGenre : genres) {
                if (!movieGenres.contains(individualGenre.trim().toLowerCase())) {
                    allGenresMatch = false;
                    break;
                }
            }

            if (allGenresMatch) {
                matches.add(movieDto);
            }
        }
        matches.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return matches;
    }


    public MovieDto getMovieById(Long movieId) {
        return movieMapper.toDto(movieRepository.getReferenceById(movieId));
    }

    public List<MovieDto> getMoviesByTimeAndLanguage(Integer start, String language) {
        List<MovieDto> matches = new ArrayList<>();
        List<MovieDto> timeMatch = getMoviesByTime(start);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);

        for (MovieDto movieDto : languageMatch) {
            if (timeMatch.contains(movieDto)) {
                matches.add(movieDto);
            }
        }
        matches.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return matches;
    }

    public List<MovieDto> getMoviesByAgeAndLanguage(Integer age, String language) {
        List<MovieDto> matches = new ArrayList<>();
        List<MovieDto> ageMatch = getMoviesByAge(age);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);

        for (MovieDto movieDto : languageMatch) {
            if (ageMatch.contains(movieDto)) {
                matches.add(movieDto);
            }
        }
        matches.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return matches;
    }

    public List<MovieDto> getMoviesByAgeAndStart(Integer age, Integer start) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> timeMatch = getMoviesByTime(start);
        List<MovieDto> ageMatch = getMoviesByAge(age);

        for (MovieDto movieDto : ageMatch) {
            if (timeMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return match;
    }

    public List<MovieDto> getMoviesByAgeStartLanguage(Integer age, Integer start, String language) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> timeMatch = getMoviesByTime(start);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);
        List<MovieDto> ageMatch = getMoviesByAge(age);

        for (MovieDto movieDto : ageMatch) {
            if (timeMatch.contains(movieDto) && languageMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return match;
    }

    public List<MovieDto> getMoviesByGenreLanguage(String genre, String language) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> genreMatch = getMoviesByGenre(genre);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);

        for (MovieDto movieDto : languageMatch) {
            if (genreMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return match;
    }

    public List<MovieDto> getMoviesByGenreStart(String genre, Integer start) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> genreMatch = getMoviesByGenre(genre);
        List<MovieDto> timeMatch = getMoviesByTime(start);

        for (MovieDto movieDto : timeMatch) {
            if (genreMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return match;
    }

    public List<MovieDto> getMoviesByGenreStartLanguage(String genre, Integer start, String language) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> genreMatch = getMoviesByGenre(genre);
        List<MovieDto> timeMatch = getMoviesByTime(start);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);

        for (MovieDto movieDto : languageMatch) {
            if (genreMatch.contains(movieDto) && timeMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return match;
    }

    public List<MovieDto> getMoviesByGenreAge(String genre, Integer age) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> genreMatch = getMoviesByGenre(genre);
        List<MovieDto> ageMatch = getMoviesByAge(age);

        for (MovieDto movieDto : ageMatch) {
            if (genreMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return match;
    }

    public List<MovieDto> getMoviesByGenreAgeLanguage(String genre, Integer age, String language) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> genreMatch = getMoviesByGenre(genre);
        List<MovieDto> ageMatch = getMoviesByAge(age);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);

        for (MovieDto movieDto : languageMatch) {
            if (genreMatch.contains(movieDto) && ageMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return match;
    }

    public List<MovieDto> getMoviesByGenreAgeStart(String genre, Integer age, Integer start) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> genreMatch = getMoviesByGenre(genre);
        List<MovieDto> ageMatch = getMoviesByAge(age);
        List<MovieDto> timeMatch = getMoviesByTime(start);

        for (MovieDto movieDto : timeMatch) {
            if (genreMatch.contains(movieDto) && ageMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation).reversed());
        return match;
    }
    public void updateMovie(Long movieId, Integer recommendation) {
        Optional<MovieEntity> optionalMovieEntity = movieRepository.findById(movieId);
        MovieDto movieDto = getMovieById(movieId);
        MovieEntity mappedMovie = movieMapper.toEntity(movieDto);

        if (optionalMovieEntity.isPresent()) {
            MovieEntity existingMovieEntity = optionalMovieEntity.get();

            existingMovieEntity.setMovieId(mappedMovie.getMovieId());
            existingMovieEntity.setMovieName(mappedMovie.getMovieName());
            existingMovieEntity.setGenre(mappedMovie.getGenre());
            existingMovieEntity.setAgeLimit(mappedMovie.getAgeLimit());
            existingMovieEntity.setLanguage(mappedMovie.getLanguage());
            existingMovieEntity.setStartTime(mappedMovie.getStartTime());
            existingMovieEntity.setRunTime(mappedMovie.getRunTime());
            existingMovieEntity.setPrice(mappedMovie.getPrice());
            existingMovieEntity.setImage(mappedMovie.getImage());
            existingMovieEntity.setDescription(mappedMovie.getDescription());
            existingMovieEntity.setRecommendation(recommendation);

            movieRepository.save(existingMovieEntity);
        }

    }


    public Page<MovieDto> getMoviesFilteredPage(Integer age, Pageable pageable) {
        Page<MovieEntity> page = movieRepository.findAllOrderByRecommendation(age, pageable);
        return movieMapper.toDtoPage(page);
    }
}
