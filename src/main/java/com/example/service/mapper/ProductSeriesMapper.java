package com.example.service.mapper;


import com.example.service.dto.ProductSeries;
import com.example.service.model.Color;
import com.example.service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSeriesMapper {
    ProductSeriesMapper INSTANCE = Mappers.getMapper(ProductSeriesMapper.class);

    @Mapping(expression = "java(product.mapSourceToSeries())", target = "series")
    @Mapping(expression = "java(product.mapColorToImageColor())", target = "imageUrl")
    @Mapping(expression = "java(product.mapSourceToImageTier())", target = "imageTier")
    ProductSeries map(Product product);

    List<ProductSeries> mapList(List<Product> products);

}
