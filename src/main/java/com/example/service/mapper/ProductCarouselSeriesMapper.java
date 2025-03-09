package com.example.service.mapper;

import com.example.service.dto.ProductCarouselSeriesDTO;
import com.example.service.dto.ProductSeries;
import com.example.service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCarouselSeriesMapper {
    ProductCarouselSeriesMapper INSTANCE = Mappers.getMapper(ProductCarouselSeriesMapper.class);

    @Mapping(source = "skin.imageTier", target = "imageTier")
    @Mapping(source = "skin.series", target = "series")
    @Mapping(source = "imageUpload.url", target = "imageUrl")
    ProductCarouselSeriesDTO map(Product product);

    List<ProductCarouselSeriesDTO> mapList(List<Product> products);
}
