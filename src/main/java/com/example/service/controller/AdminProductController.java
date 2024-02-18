package com.example.service.controller;


import com.example.service.controller.interfaces.IAdminProductController;
import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import com.example.service.request.CreateProductRequest;
import com.example.service.request.UpdateProductRequest;
import com.example.service.response.ApiResponse;
import com.example.service.service.implementation.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminProductController implements IAdminProductController {

    @Autowired
    private ProductService productService;

    @Override
    public Product createProduct(CreateProductRequest req) {
        return productService.createProduct(req);
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
    public Product updateProduct(UpdateProductRequest req, Long productId) throws ProductException {
        return productService.updateProduct(productId, req);
    }

    @Override
    public ApiResponse createMultipleProduct(CreateProductRequest[] req) {
        for (CreateProductRequest productRequest: req) {
            productService.createProduct(productRequest);
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("create multiple product successfully");
        apiResponse.setStatus(true);
        return apiResponse;
    }
}
