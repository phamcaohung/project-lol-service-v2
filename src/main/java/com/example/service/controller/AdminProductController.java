package com.example.service.controller;


import com.example.service.controller.interfaces.IAdminProductController;
import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import com.example.service.request.*;
import com.example.service.response.ApiResponse;
import com.example.service.service.implementation.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@Controller
public class AdminProductController implements IAdminProductController {

    @Autowired
    private ProductService productService;


    @Override
    public Product createProduct(CreateProductRequest product, MultipartFile imageFile) throws SQLException, IOException {
        return productService.createProduct(product, imageFile);
    }

    @Override
    public ApiResponse deleteProduct(Long productId) throws ProductException {
        productService.deleteProduct(productId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("delete product successfully");
        apiResponse.setStatus(true);
        return apiResponse;
    }

    @Override
    public Product updateProduct(UpdateProductRequest product, Long productId, MultipartFile imageFile) throws ProductException, IOException {
        return productService.updateProduct(productId, product, imageFile);
    }
}
