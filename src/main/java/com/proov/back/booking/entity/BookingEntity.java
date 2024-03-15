package com.proov.back.booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "booking")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;
    @Column(name = "client")
    private String client;
    @Column(name = "movie_id")
    private Integer movieId;
    @Column(name = "seats_id")
    private String seatsId;
    @Column(name = "price")
    private Integer price;
}
