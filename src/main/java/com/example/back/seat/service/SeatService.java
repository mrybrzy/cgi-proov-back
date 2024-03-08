package com.example.back.seat.service;

import com.example.back.seat.dto.SeatDto;
import com.example.back.seat.dto.SeatMapper;
import com.example.back.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    public List<SeatDto> getSeatsByMovieId(Long movieId) {
        List<SeatDto> allSeats = seatMapper.toDtoList(seatRepository.findAll());
        List<SeatDto> seatsByMovie = new ArrayList<>();
        for (SeatDto seatDto : allSeats) {
            if (seatDto.movieId().equals(movieId)) {
                seatsByMovie.add(seatDto);
            }
        }
        return seatsByMovie;
    }

    public List<Integer> getRecommendedSeats(Long movieId, Integer capacity) {
        List<SeatDto> seats = getSeatsByMovieId(movieId);
//        List<Integer> recommendedSeats = new ArrayList<>();
//        int totalSeats = seats.size();
//
//        for (int i = 0; i < totalSeats - capacity + 1; i++) {
//            if (!seats.get(i).isBooked()) {
//                boolean validRecommendation = true;
//
//                for (int j = i; j < i + capacity; j++) {
//                    if (seats.get(j).isBooked()) {
//                        validRecommendation = false;
//                        break;
//                    }
//                }
//
//                if (validRecommendation) {
//                    for (int j = i; j < i + capacity; j++) {
//                        recommendedSeats.add(j + 1); // Adding seat number (1-indexed)
//                    }
//                    break; // Recommend the first valid set of seats found
//                }
//            }
//        }
//        System.out.println(recommendedSeats);
//        return recommendedSeats;
        List<Integer> bestRecommendation = new ArrayList<>();
        int bestDistance = Integer.MAX_VALUE;

        int numRows = 7; // Assuming 6 rows
        int numCols = 10; // Assuming 10 columns

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col <= numCols - capacity; col++) {
                int startIndex = row * numCols + col;
                if (!seats.get(startIndex).isBooked()) {
                    int distance = calculateDistance(row, col, capacity);
                    if (distance < bestDistance) {
                        bestDistance = distance;
                        bestRecommendation = new ArrayList<>();
                        for (int j = 0; j < capacity; j++) {
                            bestRecommendation.add(startIndex + j);
                        }
                    }
                }
            }
        }

        return bestRecommendation;
    }
    private static int calculateDistance(int row, int col, int capacity) {
        int middleRow = 4; // Assuming 6 rows, middle row is 3
        int middleCol = col + capacity / 2;
        int rowDistance = Math.abs(middleRow - row);
        int colDistance = Math.abs(middleCol - col);
        return rowDistance + colDistance;
    }
}
