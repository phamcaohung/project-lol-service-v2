package com.example.service.mapper;

import com.example.service.dto.OrderDTO;
import com.example.service.dto.OrderHistoryDTO;
import com.example.service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderHistoryMapper {
    OrderHistoryMapper INSTANCE = Mappers.getMapper(OrderHistoryMapper.class);

    @Mapping(source = "publicId", target = "id")
    OrderHistoryDTO map(Order order);

    List<OrderHistoryDTO> mapList(List<Order> orders);
}
