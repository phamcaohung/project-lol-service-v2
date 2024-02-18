package com.example.service.mapper;


import com.example.service.dto.CartItemDTO;
import com.example.service.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItemDTO map(CartItem cartItem);

    List<CartItemDTO> mapList(List<CartItem> cartItems);
}
