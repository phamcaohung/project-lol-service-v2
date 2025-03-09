package com.example.service.controller.interfaces;


import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import com.example.service.request.*;
import com.example.service.response.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/api/admin/products")
@RestController
public interface IAdminProductController {
    @PostMapping("/")
    Product createProduct(@ModelAttribute CreateProductRequest req,
                          @RequestParam("imageFile") MultipartFile imageFile) throws SQLException, IOException;

    @DeleteMapping("/{productId}/delete")
    ApiResponse deleteProduct(@PathVariable Long productId) throws ProductException;

    @GetMapping("/all")
    List<Product> findAllProduct();

    @PutMapping("/{productId}/update")
    Product updateProduct(@RequestPart UpdateProductRequest product, @PathVariable Long productId,
                                 @RequestPart MultipartFile imageFile) throws ProductException, IOException;

    @PostMapping("/create")
    ApiResponse createMultipleProduct(@RequestBody CreateProductRequest[] req) throws SQLException, IOException;

    @PutMapping("/{productId}/updateImageFile")
    Product updateImageFileProduct(@PathVariable Long productId, @RequestParam("file") MultipartFile file) throws ProductException, IOException;

    @PutMapping("/multiple/updateImageFile")
    ApiResponse updateMultipleImageFileProduct(@RequestParam("productId[]") Long[] productId, @RequestParam("file[]") MultipartFile[] file) throws IOException, ProductException;
}
