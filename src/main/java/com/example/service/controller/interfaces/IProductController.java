package com.example.service.controller.interfaces;


import com.example.service.dto.ProductCarouselSeriesDTO;
import com.example.service.dto.ProductDTO;
import com.example.service.dto.ProductSeries;
import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public interface IProductController {
    @GetMapping("")
    Page<ProductDTO> findProductByCategory(@RequestParam String category, @RequestParam List<String> tier,
                                           @RequestParam Integer minPrice, @RequestParam Integer maxPrice,
                                           @RequestParam Integer minDiscount, @RequestParam String sort,
                                           @RequestParam String name, @RequestParam String series, @RequestParam String stock,
                                           @RequestParam Integer pageNumber, @RequestParam Integer pageSize);

    @GetMapping("/{id}/{productId}")
    Product findProductById(@PathVariable Long productId) throws ProductException;

    @GetMapping("/series")
    List<ProductSeries> findProductBySeriesAndIdNot(@RequestParam String series, @RequestParam Long id) throws ProductException;

    @GetMapping("/carousel")
    List<ProductCarouselSeriesDTO> findProductBySeries(@RequestParam String series) throws ProductException;

    @GetMapping("/series/all")
    List<String> getAllSeriesName(@RequestParam String category) throws ProductException;

    @GetMapping("/new")
    List<ProductSeries> getNewSkinProduct(@RequestParam String category) throws ProductException;
}

