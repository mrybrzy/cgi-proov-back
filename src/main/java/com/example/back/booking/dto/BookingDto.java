package com.example.back.booking.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public record BookingDto(Long bookingId, String client, Integer movieId, String seatsId, Integer price) {
}
