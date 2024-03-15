package com.example.back.seat.service;

import com.example.back.seat.dto.SeatDto;
import com.example.back.seat.dto.SeatMapper;
import com.example.back.seat.entity.SeatEntity;
import com.example.back.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    Map<Integer, List<Integer>> sumsAndCombinationsConsecutive = new HashMap<>();
    Map<Integer, List<Integer>> sumsAndCombinationsNotConsecutive = new HashMap<>();

    /**
     * Get all seats by movie id.
     * Iterate though all movies and pick the one which id is the same as the input id.
     *
     * @param movieId id of the movie which seats must be returned.
     * @return list of seats of the certain movie as dto objects.
     */
    public List<SeatDto> getSeatsByMovieId(Long movieId) {
        List<SeatDto> allSeats = seatMapper.toDtoList(seatRepository.findAll());
        List<SeatDto> seatsByMovie = new ArrayList<>();
        for (SeatDto seatDto : allSeats) {
            if (seatDto.movieId().equals(movieId)) {
                seatsByMovie.add(seatDto);
            }
        }
        seatsByMovie.sort(Comparator.comparingInt(SeatDto::seatNumber));
        return seatsByMovie;
    }

    /**
     * Book seat by its id.
     * Get movie by id and book certain seat which id is given.
     *
     * @param seatId  id of the seat to book.
     * @param movieId id of the movie which seat must be booked.
     */
    public void bookSeat(Long seatId, Long movieId) {
        List<SeatDto> seats = getSeatsByMovieId(movieId);
        for (SeatDto seat : seats) {
            if (seat.seatId() == seatId) {
                seatRepository.deleteById(seatId);
                SeatEntity entity = seatMapper.toEntity(seat);
                entity.setIsBooked(true);
                seatRepository.save(entity);


            }
        }
    }

    /**
     * Get recommended seats.
     * Get movie by its id and recommend the best seats to the user.
     * Best seats meaning they are next to each other, in the front of the room both vertically and horizontally.
     *
     * @param movieId  id of the movie from which seats must be recommended.
     * @param capacity number of seats to recommend.
     * @return list that contains number of seats that meets the parameters.
     */
    public List<Integer> getRecommendedSeats(Long movieId, Integer capacity) {
        List<Integer> bestCombination;
        List<SeatDto> seats = getSeatsByMovieId(movieId);
        List<SeatDto> available = new ArrayList<>();
        for (SeatDto seatDto : seats) {
            if (!seatDto.isBooked()) {
                available.add(seatDto);
            }
        }

        // clear in case of them being full from last call
        sumsAndCombinationsConsecutive.clear();
        sumsAndCombinationsNotConsecutive.clear();

        // create list of seat combinations, every combination is a list of seat numbers

        // find combinations of seats that are not booked
        List<List<Integer>> consecutiveSeatsAvailable = findConsecutiveSeatRanges(available, capacity);

        // find combinations of all seats regardless booked or not
        List<List<Integer>> consecutiveSeats = findConsecutiveSeatRanges(seats, capacity);

        // calculate middle value of all consecutive combinations being sum of numbers on seats,
        // middle value will be ideal meaning closest to the center
        float middle = getMiddleValue(consecutiveSeats);

        getSumsForCombinations(consecutiveSeatsAvailable);


        // if there is no combination with consecutive seats, use not consecutive
        if (!sumsAndCombinationsConsecutive.isEmpty()) {
            bestCombination = getBestCombination(sumsAndCombinationsConsecutive, middle);
        } else {
            bestCombination = getBestCombination(sumsAndCombinationsNotConsecutive, middle);
        }

        return bestCombination;
    }


    /**
     * Find consecutive seat ranges in case of certain number of tickets requested.
     * Find all combinations of consecutive seats so every combination will have same number of seats as requested.
     *
     * @param seatList         list of seats from where to find combinations.
     * @param ticketsRequested number of tickets (seats) requested
     * @return list of combinations of consecutive seats.
     */
    public static List<List<Integer>> findConsecutiveSeatRanges(List<SeatDto> seatList, int ticketsRequested) {
        List<List<Integer>> consecutiveSeatRanges = new ArrayList<>();

        for (int i = 0; i < seatList.size(); i++) {
            List<Integer> currentRange = new ArrayList<>();

            for (int j = i; j < i + ticketsRequested; j++) {
                if (j < seatList.size()) {
                    SeatDto seat = seatList.get(j);
                    currentRange.add(seat.seatNumber());
                }
            }
            if (currentRange.size() == ticketsRequested) {
                consecutiveSeatRanges.add(currentRange);
            }
        }
        return consecutiveSeatRanges;
    }

    /**
     * Get middle value.
     * Find the middle combination.
     * Consecutive combinations are in order meaning that the middle one is in the centre of the movie theatre.
     * Sum middle combinations seat numbers to receive middle value that will be needed when finding the best combination.
     *
     * @param allCombinations consecutive combinations that can be made with amount of tickets requested.
     * @return float value of the sum of the middle combination.
     */
    private float getMiddleValue(List<List<Integer>> allCombinations) {
        Integer listLength = allCombinations.size();
        if (listLength % 2 != 0) {
            listLength -= 1;
        }
        List<Integer> middleList = allCombinations.get(listLength / 2);
        Integer sum = middleList.stream().mapToInt(Integer::intValue).sum();
        return sum;

    }

    /**
     * Get sums for combinations.
     * Sum seat numbers of the seats in combination and put into map for later use.
     * Sum up both, consecutive and not consecutive combinations
     *
     * @param availableCombinations consecutive combinations.
     */
    private void getSumsForCombinations(List<List<Integer>> availableCombinations) {
        for (List<Integer> combination : availableCombinations) {
            int sum = combination.stream().mapToInt(Integer::intValue).sum();
            boolean consecutive = isConsecutive(combination);
            if (consecutive) {
                sumsAndCombinationsConsecutive.computeIfAbsent(sum, k -> combination);
            } else {
                sumsAndCombinationsNotConsecutive.computeIfAbsent(sum, k -> combination);
            }
        }
    }

    /**
     * Get best seat combination.
     * Iterate through combinations in the map comparing their value (summed seats numbers) to the middle value.
     * Choose the one that is the closest to the middle value as the best combination meaning the recommended seats.
     *
     * @param sumsAndCombinations map where key is seat combination and value summed up numbers of seats.
     * @param middle              middle value as the ideal sum of seats, meaning that it is in the centre.
     * @return best combination.
     */
    private List<Integer> getBestCombination(Map<Integer, List<Integer>> sumsAndCombinations, float middle) {
        List<Integer> bestMatch = new ArrayList<>();

        float smallestRange = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : sumsAndCombinations.entrySet()) {
            Integer sum = entry.getKey();
            List<Integer> combination = entry.getValue();

            if (Math.abs(middle - sum) < smallestRange) {
                smallestRange = Math.abs(middle - sum);
                bestMatch = combination;
            }
        }

        return bestMatch;
    }

    /**
     * Check if combination is consecutive.
     * Check if combination is consecutive meaning if seat numbers are next to each other.
     * If seat numbers are next to each other but in different rows, combination is not consecutive.
     *
     * @param toCheck combination that must be checked.
     * @return boolean, true in case if seats are next to each other.
     */
    private boolean isConsecutive(List<Integer> toCheck) {
        for (int i = 1; i < toCheck.size(); i++) {
            if (toCheck.get(i) != toCheck.get(i - 1) + 1) {
                return false;
            } else if (toCheck.get(i) % 10 != 0 && toCheck.get(i - 1) % 10 == 0) {
                return false;
            }
        }
        return true;
    }
}
