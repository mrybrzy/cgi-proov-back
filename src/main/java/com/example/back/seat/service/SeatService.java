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

    public List<Integer> getRecommendedSeats(Long movieId, Integer capacity) {
        List<Integer> bestCombination;
        List<SeatDto> seats = getSeatsByMovieId(movieId);
        List<SeatDto> available = new ArrayList<>();
        for (SeatDto seatDto : seats) {
            if (!seatDto.isBooked()) {
                available.add(seatDto);
            }
        }

        sumsAndCombinationsConsecutive.clear();
        sumsAndCombinationsNotConsecutive.clear();
        List<List<Integer>> consecutiveSeatsAvailable = findConsecutiveSeatRanges(available, capacity);
        List<List<Integer>> consecutiveSeats = findConsecutiveSeatRanges(seats, capacity);

        float middle = getMiddleValue(capacity, consecutiveSeats);

        getSumsForCombinations(consecutiveSeatsAvailable);

        if (!sumsAndCombinationsConsecutive.isEmpty()) {
            bestCombination = getBestCombination(sumsAndCombinationsConsecutive, middle);
        } else {
            bestCombination = getBestCombination(sumsAndCombinationsNotConsecutive, middle);
        }

        return bestCombination;
    }


    public static List<List<Integer>> findConsecutiveSeatRanges(List<SeatDto> seatList, int ticketsRequested) {
        List<List<Integer>> consecutiveSeatRanges = new ArrayList<>();

        for (int i = 0; i < seatList.size(); i++) {
            SeatDto currentSeat = seatList.get(i);
            Integer seatColumn = getColumn(currentSeat);

//            if (seatColumn + ticketsRequested - 1 <= 70) {
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
//            }
        }
        return consecutiveSeatRanges;
    }

    private float getMiddleValue(int capacity, List<List<Integer>> allCombinations) {
        Integer listLength = allCombinations.size();
        if (listLength % 2 != 0) {
            listLength -= 1;
        }
        List<Integer> middleList = allCombinations.get(listLength / 2);
        Integer sum = middleList.stream().mapToInt(Integer::intValue).sum();
        return sum;

    }

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

    private List<Integer> getBestCombination(Map<Integer, List<Integer>> sumsAndCombinations, float middle) {
        List<Integer> bestMatch = new ArrayList<>();

        float smallestRange = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry: sumsAndCombinations.entrySet()) {
            Integer sum = entry.getKey();
            List<Integer> combination = entry.getValue();

            if (Math.abs(middle - sum) < smallestRange) {
                smallestRange = Math.abs(middle - sum);
                bestMatch = combination;
            }


        }

        return bestMatch;
    }
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
    private static Integer getRow(SeatDto seatDto) {
        Integer seatNumber = seatDto.seatNumber();
        Integer row = 0;
        if (seatNumber < 10) {
            row = 1;
        } else {
            row = seatNumber / 10;
        }
        return row;
    }

    private static Integer getColumn(SeatDto seatDto) {
        Integer seatNumber = seatDto.seatNumber();
        Integer column = 0;
        if (seatNumber < 10) {
            column = seatNumber;
        } else {
            column = seatNumber % 10;
        }
        return column;
    }
}
