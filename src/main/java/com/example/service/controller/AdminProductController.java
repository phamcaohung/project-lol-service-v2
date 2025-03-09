package com.example.service.controller;


import com.example.service.controller.interfaces.IAdminProductController;
import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import com.example.service.request.*;
import com.example.service.response.ApiResponse;
import com.example.service.service.implementation.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class AdminProductController implements IAdminProductController {

    @Autowired
    private ProductService productService;


    @Override
    public Product createProduct(CreateProductRequest req, MultipartFile imageFile) throws SQLException, IOException {
        return productService.createProduct(req, imageFile);
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
    public List<Product> findAllProduct() {
        return productService.findAllProduct();
    }

    @Override
    public Product updateProduct(UpdateProductRequest product, Long productId, MultipartFile imageFile) throws ProductException, IOException {
        return productService.updateProduct(productId, product, imageFile);
    }

    @Override
    public ApiResponse createMultipleProduct(CreateProductRequest[] req) throws SQLException, IOException {
        for (CreateProductRequest productRequest: req) {
//            productService.createProduct(productRequest);
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("create multiple product successfully");
        apiResponse.setStatus(true);
        return apiResponse;
    }

    @Override
    public Product updateImageFileProduct(Long productId, @RequestParam("file") MultipartFile file) throws ProductException, IOException {
        return productService.updateImageFileProduct(productId, file);
    }

    @Override
    public ApiResponse updateMultipleImageFileProduct(Long[] productId, MultipartFile[] file) throws IOException, ProductException {
        for (int i = 0; i < productId.length; i++) {
            productService.updateImageFileProduct(productId[i], file[i]);
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("update multiple image file product successfully");
        apiResponse.setStatus(true);
        return apiResponse;
    }
}
