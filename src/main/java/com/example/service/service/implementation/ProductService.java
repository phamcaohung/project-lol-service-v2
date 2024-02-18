package com.example.service.service.implementation;

import com.example.service.dto.ProductDTO;
import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import com.example.service.request.CreateProductRequest;
import com.example.service.request.UpdateProductRequest;
import com.example.service.response.SeriesResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequest req);

    String deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, UpdateProductRequest req) throws ProductException;

    Product findProductById(Long id) throws ProductException;

    List<Product> findProductByCategory(String category);

    Page<ProductDTO> getAllProduct(String category, List<String> tier,
                                   Integer minPrice, Integer maxPrice,
                                   Integer minDiscount, String sort, String name, String series,
                                   String stock, Integer pageNumber, Integer pageSize);

    List<Product> findAllProduct();

    List<Product> findProductBySeriesAndIdNot(String series, Long id) throws ProductException;

    List<Product> findProductBySeries(String series) throws ProductException;

    List<String> findAllSeriesName() throws ProductException;

}
