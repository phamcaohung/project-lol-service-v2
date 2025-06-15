package com.example.service.mapper;

import com.example.service.dto.UserDTO;
import com.example.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO map(User user);
}
