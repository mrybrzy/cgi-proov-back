package com.proov.back.seat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "seats")
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;
    @Column(name = "movie_id")
    private Long movieId;
    @Column(name = "seat_number")
    private Integer seatNumber;
    @Column(name = "is_booked")
    private Boolean isBooked;
    @Column(name = "client")
    private String client;
}
