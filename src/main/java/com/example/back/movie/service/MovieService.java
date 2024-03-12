package com.example.back.movie.service;

import com.example.back.movie.dto.MovieDto;
import com.example.back.movie.dto.MovieMapper;
import com.example.back.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    private Integer getRecommendationPercent() {
        return 0;
    }
    public List<MovieDto> getAllMovies() {
        return movieMapper.toDtoList(movieRepository.findAll());
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

        return matches;
    }


    public MovieDto getMovieById(Long movieId) {
        return movieMapper.toDto(movieRepository.getReferenceById(movieId));
    }

    public List<MovieDto> getMoviesByTimeAndLanguage(Integer start, String language) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> timeMatch = getMoviesByTime(start);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);

        for (MovieDto movieDto : languageMatch) {
            if (timeMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        return match;
    }

    public List<MovieDto> getMoviesByAgeAndLanguage(Integer age, String language) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> ageMatch = getMoviesByAge(age);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);

        for (MovieDto movieDto : languageMatch) {
            if (ageMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        return match;
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
        return match;
    }
}
