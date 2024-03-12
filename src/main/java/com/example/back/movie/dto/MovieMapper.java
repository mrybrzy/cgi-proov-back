package com.example.back.movie.dto;

import com.example.back.movie.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {
    MovieDto toDto(MovieEntity movieEntity);

    List<MovieDto> toDtoList(List<MovieEntity> movieEntities);

    MovieEntity toEntity(MovieDto movieDto);

    default Page<MovieDto> toDtoPage(Page<MovieEntity> movieEntities) {
        List<MovieDto> dtoList = movieEntities.getContent().stream()
                .map(this::toDto) // kasutad olemasolevat toDto funktsiooni
                .toList();
        return new PageImpl<>(dtoList, movieEntities.getPageable(), movieEntities.getTotalElements());
    }
}
