package com.example.service.mapper;


import com.example.service.dto.ProductDTO;
import com.example.service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "skin.imageTier", target = "imageTier")
    @Mapping(source = "imageUpload.url", target = "imageUrl")
    ProductDTO map(Product product);

    List<ProductDTO> mapList(List<Product> products);

}
