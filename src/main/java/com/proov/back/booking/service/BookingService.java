package com.proov.back.booking.service;

import com.proov.back.booking.dto.BookingDto;
import com.proov.back.booking.dto.BookingMapper;
import com.proov.back.booking.entity.BookingEntity;
import com.proov.back.booking.repository.BookingRepository;
import com.proov.back.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final SeatService seatService;

    /**
     * Add new booking.
     * Receive booking dto with seats that have been booked.
     * Use seat service function to book certain seat.
     * Map dto to entity to store booking in database.
     * @param bookingDto dto received from frontend to book certain seats and store new booking in database.
     */
    public void addBooking(BookingDto bookingDto) {
        String[] seats = bookingDto.seatsId().split(",");
        for (String seat : seats) {
            Long seatLong = Long.valueOf(seat);
            seatService.bookSeat(seatLong, Long.valueOf(bookingDto.movieId()));
        }

        BookingEntity mapped = bookingMapper.toEntity(bookingDto);
        bookingRepository.save(mapped);
    }

    /**
     * Get all bookings.
     * @return all bookings in form of dto returned in list.
     */
    public List<BookingDto> getAllBookings() {
        return bookingMapper.toDtoList(bookingRepository.findAll());
    }

    /**
     * Get all client bookings by username.
     * Iterate through all existing bookings and get the needed ones by client username.
     * @param clientUsername client username whose bookings must be returned.
     * @return list of certain client bookings in form of dto.
     */
    public List<BookingDto> getBookingsByClient(String clientUsername) {
        List<BookingDto> allBookings = getAllBookings();
        List<BookingDto> byClient = new ArrayList<>();
        for (BookingDto dto : allBookings) {
            String bookingClient = dto.client();

            if (bookingClient.equals(clientUsername)) {
                byClient.add(dto);
            }
        }
        return byClient;
    }

    /**
     * Get booking price.
     * Multiply number of booked seats by 7 which is default price for one ticket.
     * @param seats number of seats that have been booked.
     * @return price of booking.
     */
    public Integer getPrice(Integer seats) {
        return seats * 7;
    }
}