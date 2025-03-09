package com.example.service.service.implementation;

import com.example.service.dto.ProductDTO;
import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import com.example.service.request.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequest req, MultipartFile imageFile) throws IOException, SQLException;

    String deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, UpdateProductRequest req, MultipartFile imageFile) throws ProductException, IOException;

    Product findProductById(Long id) throws ProductException;

    List<Product> findProductByCategory(String category);

    Page<ProductDTO> getAllProduct(String category, List<String> tier,
                                   Integer minPrice, Integer maxPrice,
                                   Integer minDiscount, String sort, String name, String series,
                                   String stock, Integer pageNumber, Integer pageSize);

    List<Product> findAllProduct();

    List<Product> findProductBySeriesAndIdNot(String series, Long id) throws ProductException;

    List<Product> findProductBySeries(String series) throws ProductException;

    List<String> findAllSeriesName(String series) throws ProductException;

    List<Product> findNewSkinProduct(String category) throws ProductException;

    Product updateImageFileProduct(Long productId, @RequestParam("file") MultipartFile file) throws ProductException, IOException;
}
