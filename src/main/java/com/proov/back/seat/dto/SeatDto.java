package com.proov.back.seat.dto;

public record SeatDto (Long seatId, Long movieId, Integer seatNumber, Boolean isBooked, String client){
}
