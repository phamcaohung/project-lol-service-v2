package com.example.service.mapper;


import com.example.service.dto.OrderDTO;
import com.example.service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "publicId", target = "id")
    OrderDTO map(Order order);

    List<OrderDTO> mapList(List<Order> orders);


}
