package com.example.service.mapper;


import com.example.service.dto.OrderItemDTO;
import com.example.service.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO map(OrderItem orderItem);

    List<OrderItemDTO> mapList(List<OrderItem> orderItems);
}
