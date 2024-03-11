package com.example.back.user.dto;

import com.example.back.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface UserMapper {
    UserDto toDto(UserEntity userEntity);

    List<UserDto> toDtoList(List<UserEntity> userEntities);

    UserEntity toEntity(UserDto userDto);
}
