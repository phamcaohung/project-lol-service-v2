package com.example.service.mapper;


import com.example.service.dto.ProductSeries;
import com.example.service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSeriesMapper {
    ProductSeriesMapper INSTANCE = Mappers.getMapper(ProductSeriesMapper.class);

    ProductSeries map(Product product);

    List<ProductSeries> mapList(List<Product> products);
}
