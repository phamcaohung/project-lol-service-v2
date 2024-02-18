package com.example.service.mapper;

import com.example.service.dto.ProductCart;
import com.example.service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCardMapper {
    ProductCardMapper INSTANCE = Mappers.getMapper(ProductCardMapper.class);

    ProductCart map(Product product);

    List<ProductCart> mapList(List<Product> products);
}
