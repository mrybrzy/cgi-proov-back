package com.proov.back.booking.controller;

import com.proov.back.booking.dto.BookingDto;
import com.proov.back.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/bookings/{username}")
    public List<BookingDto> getBookingsByUser(@PathVariable("username") String username) {
        return bookingService.getBookingsByClient(username);
    }

    @PostMapping("/book")
    public void addBooking(@RequestBody BookingDto bookingDto) {
        bookingService.addBooking(bookingDto);
    }

    @GetMapping("/public/price/{seats}")
    public Integer getBookingPrice(@PathVariable("seats") Integer seats) {
        return bookingService.getPrice(seats);
    }
}
