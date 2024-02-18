package com.example.service.mapper;


import com.example.service.dto.ProductCarousel;
import com.example.service.dto.ProductCart;
import com.example.service.dto.ProductDTO;
import com.example.service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO map(Product product);

    List<ProductDTO> mapList(List<Product> products);

}
