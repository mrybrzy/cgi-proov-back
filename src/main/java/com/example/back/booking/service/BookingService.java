package com.example.back.booking.service;

import com.example.back.booking.dto.BookingDto;
import com.example.back.booking.dto.BookingMapper;
import com.example.back.booking.entity.BookingEntity;
import com.example.back.booking.repository.BookingRepository;
import com.example.back.movie.dto.MovieDto;
import com.example.back.movie.service.MovieService;
import com.example.back.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final MovieService movieService;
    private final SeatService seatService;

    public void addBooking(BookingDto bookingDto) {
        String[] seats = bookingDto.seatsId().split(",");
        for (String seat : seats) {
            Long seatLong = Long.valueOf(seat);
            seatService.bookSeat(seatLong, Long.valueOf(bookingDto.movieId()));
        }

        BookingEntity mapped = bookingMapper.toEntity(bookingDto);
        bookingRepository.save(mapped);


    }

    public List<BookingDto> getAllBookings() {
        return bookingMapper.toDtoList(bookingRepository.findAll());
    }


//    public List<BookingDto> bookingDateOverlaps(List<BookingDto> bookingsByHotel, String in, String out) {
//        List<BookingDto> dateOverlapBookings = new ArrayList<>();
//        for (BookingDto booking : bookingsByHotel) {
//            if (isOverlap(booking, in, out)) {
//                dateOverlapBookings.add(booking);
//            }
//        }
//        return dateOverlapBookings;
//    }

    //    public boolean isOverlap(BookingDto existingBooking, String in, String out) {
//
//        LocalDate dateINn = LocalDate.parse(in);
//        LocalDate dateOut = LocalDate.parse(out);
//        return !(LocalDate.parse(existingBooking.checkOut()).isBefore(dateINn) || dateOut.isBefore(LocalDate.parse(existingBooking.checkIn())));
//    }
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
//    public List<BookingDto> getBookingByMovieId(Long hotelId) {
//        List<BookingDto> allBookings = getAllBookings();
//        List<BookingDto> byHotelId = new ArrayList<>();
//        for (BookingDto dto : allBookings) {
//            Long bookingRoomId = dto.hotelId();
//
//            if (bookingRoomId.equals(hotelId)) {
//                byHotelId.add(dto);
//            }
//        }
//        return byHotelId;
//
//
//    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public Integer getPrice(Integer seats) {
        return seats * 7;
    }
}