package com.proov.back.booking.dto;

public record BookingDto(Long bookingId, String client, Integer movieId, String seatsId, Integer price) {
}
