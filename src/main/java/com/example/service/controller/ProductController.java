package com.example.service.controller;

import com.example.service.controller.interfaces.IProductController;
import com.example.service.dto.ProductCarousel;
import com.example.service.dto.ProductDTO;
import com.example.service.dto.ProductSeries;
import com.example.service.exception.ProductException;
import com.example.service.mapper.ProductCarouselMapper;
import com.example.service.mapper.ProductMapper;
import com.example.service.mapper.ProductSeriesMapper;
import com.example.service.model.Product;
import com.example.service.response.SeriesResponse;
import com.example.service.service.implementation.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController implements IProductController {

    @Autowired
    private ProductService productService;


    @Override
    public Page<ProductDTO> findProductByCategory(String category, List<String> tier,
                                                  Integer minPrice, Integer maxPrice, Integer minDiscount,
                                                  String sort, String name, String series, String stock,
                                                  Integer pageNumber, Integer pageSize) {
        return productService.getAllProduct(category, tier, minPrice, maxPrice,
                                            minDiscount, sort, name, series, stock,
                                            pageNumber, pageSize);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        return productService.findProductById(productId);
    }

    @Override
    public List<ProductSeries> findProductBySeriesAndIdNot(String series, Long id) throws ProductException {
        String trimmedSeries = series.trim();
        List<Product> products =  productService.findProductBySeriesAndIdNot(trimmedSeries, id);
        return ProductSeriesMapper.INSTANCE.mapList(products);
    }

    @Override
    public List<ProductCarousel> findProductBySeries(String series) throws ProductException {
        List<Product> products = productService.findProductBySeries(series);
        return ProductCarouselMapper.INSTANCE.mapList(products);
    }

    @Override
    public List<String> getAllSeries() throws ProductException {
        return productService.findAllSeriesName();
    }
}
