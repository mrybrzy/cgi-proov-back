package com.proov.back.seat.dto;

import com.proov.back.seat.entity.SeatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SeatMapper {
    SeatDto toDto(SeatEntity seatEntity);

    List<SeatDto> toDtoList(List<SeatEntity> seatEntities);

    SeatEntity toEntity(SeatDto seatDto);
}
