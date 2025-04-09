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

    Page<ProductDTO> getAllProduct(FilterProductRequest request);

    List<Product> findSkinProductBySeriesAndIdNot(String series, Long id) throws ProductException;

    List<Product> findChampionProductBySeriesAndIdNot(String region, Long id) throws ProductException;

    List<Product> findChibiProductBySeriesAndIdNot(String champion, Long id) throws ProductException;

    List<Product> findProductBySeries(String series) throws ProductException;

    List<String> findAllSeriesName(String category) throws ProductException;

    List<Product> findNewSkinProduct(String category) throws ProductException;
}
