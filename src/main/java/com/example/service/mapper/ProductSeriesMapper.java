package com.example.service.mapper;


import com.example.service.dto.ProductSeries;
import com.example.service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSeriesMapper {
    ProductSeriesMapper INSTANCE = Mappers.getMapper(ProductSeriesMapper.class);

    @Mapping(source = "skin.imageTier", target = "imageTier")
    @Mapping(source = "imageUpload.url", target = "imageUrl")
    ProductSeries map(Product product);

    List<ProductSeries> mapList(List<Product> products);
}
