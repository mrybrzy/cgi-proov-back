package com.proov.back.booking.dto;

import com.proov.back.booking.entity.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {
    BookingDto toDto(BookingEntity bookingEntity);
    List<BookingDto> toDtoList(List<BookingEntity> bookingEntities);
    BookingEntity toEntity(BookingDto bookingDto);
}
