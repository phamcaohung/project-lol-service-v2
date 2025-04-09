package com.example.service.controller.interfaces;


import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import com.example.service.request.*;
import com.example.service.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@RequestMapping("/api/admin/products")
@RestController
public interface IAdminProductController {
    @PostMapping("/")
    Product createProduct(@RequestPart CreateProductRequest product,
                          @RequestPart("imageFile") MultipartFile imageFile) throws SQLException, IOException;

    @DeleteMapping("/{productId}/delete")
    ApiResponse deleteProduct(@PathVariable Long productId) throws ProductException;

    @PutMapping("/{productId}/update")
    Product updateProduct(@RequestPart UpdateProductRequest product, @PathVariable Long productId,
                          @RequestPart MultipartFile imageFile) throws ProductException, IOException;


}