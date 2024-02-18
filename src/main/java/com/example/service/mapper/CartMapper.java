package com.example.service.mapper;

import com.example.service.dto.CartDTO;
import com.example.service.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDTO map(Cart cart);

}
