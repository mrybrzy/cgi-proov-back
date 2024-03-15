package com.example.back.movie.service;

import com.example.back.movie.dto.MovieDto;
import com.example.back.movie.dto.MovieMapper;
import com.example.back.movie.entity.MovieEntity;
import com.example.back.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    /**
     * Get all movies.
     * Get all movies that are stored in database sorted by its recommendation and rating in descending order.
     *
     * @return list of sorted movies in descending order.
     */
    public List<MovieDto> getAllMovies() {
        List<MovieDto> all = movieMapper.toDtoList(movieRepository.findAll());
        all.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return all;
    }

    /**
     * Get movies filtered by genres, age limit, start time and language which are users input values.
     *
     * @param genre    string of genres in which user is interested, that are separated by comma
     * @param age      integer representing age limit, meaning that client wants to see movies that are allowed for certain age
     *                 (for example if clients input for age limit is 18, the function will return 12, 15 and 18 rated
     *                 movies because all of those are allowed in this age)
     * @param start    integer value of start time of the movie (all the movies that start past this time will be considered)
     * @param language language of the movie
     * @return list of movies that satisfy demands, sorted by recommendation value and rating in descending order.
     */
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
        matches.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return matches;

    }

    /**
     * Get movies by language.
     *
     * @param language desired language.
     * @return list of movies that are in desired language, sorted by recommendation value and rating in descending order.
     */
    public List<MovieDto> getMoviesByLanguage(String language) {
        List<MovieDto> movieDtoList = getAllMovies();
        List<MovieDto> matches = new ArrayList<>();
        for (MovieDto movieDto : movieDtoList) {
            if (movieDto.language().equalsIgnoreCase(language)) {
                matches.add(movieDto);
            }
        }
        matches.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return matches;
    }

    /**
     * Get movies by time.
     * Get movies that start at or past the desired time.
     *
     * @param start desired start time of the movie.
     * @return list of movies that start at or past the desired time, sorted by recommendation value and rating in descending order.
     */
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
        matches.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return matches;
    }

    /**
     * Get movies by age.
     * Get movies which age limit is same as desired and which age limit is smaller than desired.
     *
     * @param age desired age limit.
     * @return list of movies that satisfy the desired age limit, sorted by recommendation value and rating in descending order.
     */
    public List<MovieDto> getMoviesByAge(Integer age) {
        List<MovieDto> movieDtoList = getAllMovies();
        List<MovieDto> matches = new ArrayList<>();
        for (MovieDto movieDto : movieDtoList) {
            if (movieDto.ageLimit() <= age) {
                matches.add(movieDto);
            }
        }
        matches.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return matches;
    }

    /**
     * Get movies by genre.
     * Get movies that contain desired genre(s).
     *
     * @param genre desired genre(s).
     * @return list of movies that contain desired genre(s), sorted by recommendation value and rating in descending order.
     */
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
        matches.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return matches;
    }


    /**
     * Get movie by id.
     * Get movie with desired id.
     *
     * @param movieId desired movie id.
     * @return movie dto.
     */
    public MovieDto getMovieById(Long movieId) {
        return movieMapper.toDto(movieRepository.getReferenceById(movieId));
    }

    /**
     * Get movies desired by start time and language.
     *
     * @param start    desired start time.
     * @param language desired language.
     * @return list og movies that are in the desired language and start at or past the desired start time,
     * sorted by recommendation value and rating in descending order.
     */
    public List<MovieDto> getMoviesByTimeAndLanguage(Integer start, String language) {
        List<MovieDto> matches = new ArrayList<>();
        List<MovieDto> timeMatch = getMoviesByTime(start);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);

        for (MovieDto movieDto : languageMatch) {
            if (timeMatch.contains(movieDto)) {
                matches.add(movieDto);
            }
        }
        matches.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return matches;
    }

    /**
     * Get movies by desired age limit and language.
     *
     * @param age      desired age limit.
     * @param language desired language.
     * @return list of movies that are in given language and have same or smaller given age limit,
     * sorted by recommendation value and rating in descending order.
     */
    public List<MovieDto> getMoviesByAgeAndLanguage(Integer age, String language) {
        List<MovieDto> matches = new ArrayList<>();
        List<MovieDto> ageMatch = getMoviesByAge(age);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);

        for (MovieDto movieDto : languageMatch) {
            if (ageMatch.contains(movieDto)) {
                matches.add(movieDto);
            }
        }
        matches.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return matches;
    }

    /**
     * Get movies by age limit and age limit and start time.
     *
     * @param age   desired age limit.
     * @param start desired start time.
     * @return list of movies that start at or past the given start time and have same or smaller given age limit,
     * sorted by recommendation value and rating in descending order.
     */
    public List<MovieDto> getMoviesByAgeAndStart(Integer age, Integer start) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> timeMatch = getMoviesByTime(start);
        List<MovieDto> ageMatch = getMoviesByAge(age);

        for (MovieDto movieDto : ageMatch) {
            if (timeMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return match;
    }

    /**
     * Get movies by age limit, start time and language.
     *
     * @param age      desired age limit.
     * @param start    desired start time.
     * @param language desired language.
     * @return list of movies in given language, starting at or past given time and having same or smaller age limit as given,
     * sorted by recommendation value and rating in descending order.
     */
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
        match.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return match;
    }

    /**
     * Get movies by genre and language.
     *
     * @param genre    desired genre(s).
     * @param language desired language.
     * @return list of movies that contain given genre(s) and are in given language,
     * sorted by recommendation value and rating in descending order.
     */
    public List<MovieDto> getMoviesByGenreLanguage(String genre, String language) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> genreMatch = getMoviesByGenre(genre);
        List<MovieDto> languageMatch = getMoviesByLanguage(language);

        for (MovieDto movieDto : languageMatch) {
            if (genreMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return match;
    }

    /**
     * Get movies by genre(s) and start time.
     *
     * @param genre desired genre(s).
     * @param start desired start time.
     * @return list of movies containing given genre(s) and starting at or past given start time,
     * sorted by recommendation value and rating in descending order.
     */
    public List<MovieDto> getMoviesByGenreStart(String genre, Integer start) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> genreMatch = getMoviesByGenre(genre);
        List<MovieDto> timeMatch = getMoviesByTime(start);

        for (MovieDto movieDto : timeMatch) {
            if (genreMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return match;
    }

    /**
     * Get movies by genre(s), start time and language.
     *
     * @param genre    desired genre(s).
     * @param start    desired start time.
     * @param language desired language.
     * @return list of movies in given language, starting at or past the given start time, containing given genre(s).
     * sorted by recommendation value and rating in descending order.
     */
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
        match.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return match;
    }

    /**
     * Get movies by age limit and genre(s).
     *
     * @param genre desired genre(s).
     * @param age   desired age limit.
     * @return list of movies containing given genre(s), having same or smaller age limit that given,
     * sorted by recommendation value and rating in descending order.
     */
    public List<MovieDto> getMoviesByGenreAge(String genre, Integer age) {
        List<MovieDto> match = new ArrayList<>();
        List<MovieDto> genreMatch = getMoviesByGenre(genre);
        List<MovieDto> ageMatch = getMoviesByAge(age);

        for (MovieDto movieDto : ageMatch) {
            if (genreMatch.contains(movieDto)) {
                match.add(movieDto);
            }
        }
        match.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return match;
    }

    /**
     * Get movies by genre(s), age limit and language.
     *
     * @param genre    desired genre(s).
     * @param age      desired ahe limit.
     * @param language desired language.
     * @return list of movies on given language, containing given genre(s) and having same or smaller age limit than given,
     * sorted by recommendation value and rating in descending order.
     */
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
        match.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return match;
    }

    /**
     * Get movies by genre(s), age limit and start time.
     *
     * @param genre desired genre(s).
     * @param age   desired age limit.
     * @param start desired start time.
     * @return list of movies containing given genre(s), starting at or past the start time and having same or smaller age limit than given,
     * sorted by recommendation value and rating in descending order.
     */
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
        match.sort(Comparator.comparingInt(MovieDto::recommendation)
                .thenComparing(MovieDto::rating)
                .reversed());
        return match;
    }

    /**
     * Update movie recommendation by movie id.
     * Assign new recommendation value, keep all the other values the same.
     *
     * @param movieId        id of the movie which recommendation must be updated.
     * @param recommendation recommendation value that must be assigned to the certain movie.
     */
    public void updateMovieRecommendation(Long movieId, Integer recommendation) {
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
            existingMovieEntity.setRating(mappedMovie.getRating());

            movieRepository.save(existingMovieEntity);
        }
    }

    /**
     * Update movie rating by movie id.
     * Assign new rating, keep all the other values the same.
     *
     * @param movieId id of the movie which rating must be updated.
     * @param rating  rating value that must be assigned to the certain movie.
     */
    public void updateMovieRating(Long movieId, Float rating) {
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
            existingMovieEntity.setRecommendation(mappedMovie.getRecommendation());
            existingMovieEntity.setRating(rating);

            movieRepository.save(existingMovieEntity);
        }
    }

    /**
     * Remove movie recommendation from all movies and assign its value as zero.
     * Keep all the other values same.
     */
    public void removeRecommendation() {
        List<MovieDto> all = getAllMovies();
        for (MovieDto movie : all) {
            Long movieId = movie.movieId();
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
                existingMovieEntity.setRecommendation(0);
                existingMovieEntity.setRating(mappedMovie.getRating());

                movieRepository.save(existingMovieEntity);
            }
        }
    }
}
