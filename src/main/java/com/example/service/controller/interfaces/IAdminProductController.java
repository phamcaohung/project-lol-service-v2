package com.example.service.controller.interfaces;


import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import com.example.service.request.CreateProductRequest;
import com.example.service.request.UpdateProductRequest;
import com.example.service.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin/products")
@RestController
public interface IAdminProductController {
    @PostMapping("/")
    Product createProduct(@RequestBody CreateProductRequest req);

    @DeleteMapping("/{productId}/delete")
    ApiResponse deleteProduct(@PathVariable Long productId) throws ProductException;

    @GetMapping("/all")
    List<Product> findAllProduct();

    @PutMapping("/{productId}/update")
    Product updateProduct(@RequestBody UpdateProductRequest req, @PathVariable Long productId) throws ProductException;

    @PostMapping("/create")
    ApiResponse createMultipleProduct(@RequestBody CreateProductRequest[] req);




}
