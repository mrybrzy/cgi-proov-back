package com.proov.back.movie.dto;

import com.proov.back.movie.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {
    MovieDto toDto(MovieEntity movieEntity);

    List<MovieDto> toDtoList(List<MovieEntity> movieEntities);

    MovieEntity toEntity(MovieDto movieDto);

}
