package com.example.service.mapper;


import com.example.service.dto.ProductCarousel;
import com.example.service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCarouselMapper {
    ProductCarouselMapper INSTANCE = Mappers.getMapper(ProductCarouselMapper.class);

    ProductCarousel map(Product product);

    List<ProductCarousel> mapList(List<Product> products);
}
