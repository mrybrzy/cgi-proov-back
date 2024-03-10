package com.example.back.seat.repository;

import com.example.back.seat.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
}
