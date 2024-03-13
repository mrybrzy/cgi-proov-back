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

    public void addUser(UserDto user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userRepository.save(userEntity);
    }

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

    public void getRecommendation(String username) {
        Map<String, Integer> genresInMovies = new HashMap<>();
        List<BookingDto> bookings = bookingService.getBookingsByClient(username);
        Integer moviesWatched = bookings.size();
        Map<String, Integer> allWatched = collectAllWatchedGenres(bookings, genresInMovies);
        Map<String, Integer> percentage = calculateRecommendationPercentage(allWatched, moviesWatched);
        assignPercentage(percentage);

    }
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

    private Map<String, Integer> calculateRecommendationPercentage(Map<String, Integer> watched, Integer bookingsCount) {
        Map<String, Integer> percentageMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : watched.entrySet()) {
            Integer presentInFilms = entry.getValue();
            percentageMap.put(entry.getKey(), (int) (((float) presentInFilms / bookingsCount) * 100));
        }
        return percentageMap;
    }

    private void assignPercentage(Map<String, Integer> percentageMap) {
        List<MovieDto> allMovies = movieService.getAllMovies();
        for (MovieDto movieDto : allMovies) {
            String[] genres = movieDto.genre().split(", ");
            Long movieId = movieDto.movieId();
            putPercentage(genres, percentageMap, movieId);

        }

    }
    private void putPercentage(String[] genres, Map<String, Integer> percentageMap, Long movieId) {
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
