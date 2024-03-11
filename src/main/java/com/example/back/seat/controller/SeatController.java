package com.example.back.seat.controller;

import com.example.back.seat.dto.SeatDto;
import com.example.back.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @GetMapping("/movie/id/{movie_id}")
    public List<SeatDto> getSeatsByMovie(@PathVariable("movie_id") Long movieId) {
        return seatService.getSeatsByMovieId(movieId);
    }
    @GetMapping("/movie/{movie_id}/{capacity}")
    public List<Integer> getRecommendedSeats(@PathVariable("movie_id") Long movieId,
                                             @PathVariable("capacity") Integer capacity) {
        return seatService.getRecommendedSeats(movieId, capacity);
    }
}
