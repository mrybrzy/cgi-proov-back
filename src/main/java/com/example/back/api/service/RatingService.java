package com.example.back.api.service;

import com.example.back.movie.dto.MovieDto;
import com.example.back.movie.service.MovieService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final MovieService movieService;

    public void assignRating() {
        List<MovieDto> movieDtoList = movieService.getAllMovies();
        for (MovieDto movieDto : movieDtoList) {
            Float rating = getRating(movieDto.movieName());
            movieService.updateMovieRating(movieDto.movieId(), rating);
        }


    }
    public Float getRating(String movieName) {
        String rating = "";
        movieName = movieName.replace(" ", "+");
        String apiUrl = "http://www.omdbapi.com/?t=" + movieName + "&apikey=e26f67";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(in.readLine());
            rating = jsonObject.get("imdbRating").toString();



        } catch (Exception e) {
            e.printStackTrace();
        }
        Float responseFloat = Float.valueOf(rating);
//        String formatted = String.format("%.2f", responseFloat);
        return responseFloat;
    }
}
