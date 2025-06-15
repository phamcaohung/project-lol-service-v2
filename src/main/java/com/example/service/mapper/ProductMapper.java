package com.example.service.mapper;


import com.example.service.dto.ProductDTO;
import com.example.service.model.Color;
import com.example.service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.stream.Collectors;



@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "champion.role", target = "role")
    @Mapping(source = "champion.imageRole", target = "imageRole")
    @Mapping(source = "champion.region", target = "region")
    @Mapping(source = "champion.imageRegion", target = "imageRegion")
    @Mapping(expression = "java(product.mapColorToListColor())", target = "colors")
    @Mapping(expression = "java(product.mapColorToImageColor())", target = "imageUrl")
    @Mapping(expression = "java(product.mapSourceToImageTier())", target = "imageTier")
    @Mapping(expression = "java(product.mapSourceToSeries())", target = "series")
    ProductDTO map(Product product);

    List<ProductDTO> mapList(List<Product> products);

}
