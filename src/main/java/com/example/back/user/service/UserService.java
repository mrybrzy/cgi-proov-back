package com.example.back.user.service;

import com.example.back.booking.dto.BookingDto;
import com.example.back.booking.service.BookingService;
import com.example.back.exception.ApplicationException;
import com.example.back.movie.dto.MovieDto;
import com.example.back.movie.service.MovieService;
import com.example.back.security.JwtTokenProvider;
import com.example.back.user.dto.UserDto;
import com.example.back.user.dto.UserMapper;
import com.example.back.user.dto.UserMapperImpl;
import com.example.back.user.entity.UserEntity;
import com.example.back.user.repository.UserRepository;
import com.example.back.user.request.CreateUserRequest;
import com.example.back.user.request.LoginUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BookingService bookingService;
    private final MovieService movieService;
    private final UserMapper userMapper = new UserMapperImpl();
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Add user.
     * Add user using dto received from frontend.
     * @param user data of user to add.
     */
    public void addUser(UserDto user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userRepository.save(userEntity);
    }

    /**
     * Register user.
     * Check if user already exists.
     * Check if password is the same as repeated password during registration.
     * Encode password and save user.
     * @param request registration request.
     */
    public void registerUser(CreateUserRequest request) {
        Optional<UserEntity> existingUser = userRepository.findById(request.getUsername());
        if (existingUser.isPresent()) {
            log.error("User with this username already exists!");
            throw new ApplicationException("User with this username already exists!");
        }
        if (!Objects.equals(request.getPassword(), request.getPasswordRepeated())) {
            log.error("Password incorrect!");
            throw new ApplicationException("Password incorrect!");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        UserDto user = new UserDto(request.getUsername(), request.getName(), encodedPassword);
        log.info("Register successful!");
        addUser(user);
    }

    /**
     * Login user.
     * Check username and password.
     * @param request login request.
     * @return jwt token.
     */
    public String login(LoginUserRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            log.error("Username or password is not provided!");
            throw new ApplicationException("Username or password is not provided!");
        }

        Optional<UserEntity> user = userRepository.findById(request.getUsername());
        if (user.isEmpty()) {
            log.error("Wrong username!");
            throw new ApplicationException("Wrong username!");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            log.error("Wrong password!");
            throw new ApplicationException("Wrong password!");
        }
        log.info("Login successful!");
        return jwtTokenProvider.getToken(user.get());
    }

    /**
     * Get user by username.
     * @param username username.
     * @return user dto.
     */
    public UserDto getUserByUsername(String username) {
        List<UserDto> userDtos = userMapper.toDtoList(userRepository.findAll());
        UserDto match = null;
        for (UserDto userDto : userDtos) {
            if (userDto.username().equals(username)) {
                match = userDto;
            }
        }
        return match;
    }

    /**
     * Get recommendation.
     * Recommendation is percentage that is based on the genres that user have watched previously.
     * Percentage is different and depends on the genres that certain movie has.
     * Use other functions to do the calculations.
     *
     * @param username user username, for whom percentage is calculated.
     */
    public void getRecommendation(String username) {
        Map<String, Integer> genresInMovies = new HashMap<>();
        List<BookingDto> bookings = bookingService.getBookingsByClient(username);
        Integer moviesWatched = bookings.size();

        // collect all watched genres to the map where key is genre and value is how much time was it watched
        Map<String, Integer> allWatched = collectAllWatchedGenres(bookings, genresInMovies);

        // calculates percentage of how much times certain genre was watched
        Map<String, Integer> percentage = calculatePercentage(allWatched, moviesWatched);

        assignPercentage(percentage);

    }

    /**
     * Collect all watched genres.
     * Collect all genres using user bookings to map where key is genre and value is
     * how many movies with this genre was watched.
     * @param bookings user bookings from where data is collected.
     * @param genresInMovies empty map to fill it with genres and times it was watched.
     * @return map that is updated with information as what genres were watched how many times.
     */
    private Map<String, Integer> collectAllWatchedGenres(List<BookingDto> bookings, Map<String, Integer> genresInMovies) {
        for (BookingDto bookingDto : bookings) {
            MovieDto movieDto = movieService.getMovieById(Long.valueOf(bookingDto.movieId()));
            String[] genres = movieDto.genre().split(", ");
            for (String genre : genres) {
                genresInMovies.put(genre.toLowerCase(), genresInMovies.getOrDefault(genre.toLowerCase(), 0) + 1);
            }
        }
        return genresInMovies;
    }

    /**
     * Calculate movie percentage.
     * Take every value of map that represents how many times the genre was watched.
     * Divide it by amount on movies watched and multiply by 100 to get percentage of how many bookings contain this genre.
     * @param watched map with information about how many times genres were watched.
     * @param bookingsCount amount of bookings of certain user.
     * @return map that contains genre as key and percentage af value.
     */
    private Map<String, Integer> calculatePercentage(Map<String, Integer> watched, Integer bookingsCount) {
        Map<String, Integer> percentageMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : watched.entrySet()) {
            Integer presentInFilms = entry.getValue();
            percentageMap.put(entry.getKey(), (int) (((float) presentInFilms / bookingsCount) * 100));
        }
        return percentageMap;
    }

    /**
     * Assign percentage to movies.
     * Assign percentage to movies by iterating through all of them.
     * Take genre of the certain movie and movie id to use them in the calculation.
     * @param percentageMap map with percentage of watching certain genres.
     */
    private void assignPercentage(Map<String, Integer> percentageMap) {
        List<MovieDto> allMovies = movieService.getAllMovies();
        for (MovieDto movieDto : allMovies) {
            String[] genres = movieDto.genre().split(", ");
            Long movieId = movieDto.movieId();
            calculateAndPutRecommendationPercentage(genres, percentageMap, movieId);

        }

    }

    /**
     * Calculate recommendation percentage and assign as value.
     * Calculation consists of taking percentage values by genre which is present in the certain movie,
     * Calculate average by compounding percentages and dividing by amount of genres in certain movie
     * To get recommendation percentage.
     * Assign calculated recommendation as recommendation value of the movie.
     * @param genres genres present in certain movie.
     * @param percentageMap map that contains all watched genres and their percentage.
     * @param movieId id of the movie which percentage is assigned.
     */
    private void calculateAndPutRecommendationPercentage(String[] genres, Map<String, Integer> percentageMap, Long movieId) {
        Integer per = 0;
        for (String genre : genres) {
            if (percentageMap.containsKey(genre.toLowerCase())) {
                per += percentageMap.get(genre.toLowerCase());
            }
        }
        per = per / genres.length;
        movieService.updateMovieRecommendation(movieId, per);

    }
}
