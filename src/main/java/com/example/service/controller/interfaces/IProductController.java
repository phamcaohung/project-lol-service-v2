package com.example.service.controller.interfaces;


import com.example.service.dto.ProductCarouselSeriesDTO;
import com.example.service.dto.ProductDTO;
import com.example.service.dto.ProductSeries;
import com.example.service.exception.ProductException;
import com.example.service.model.Product;
import com.example.service.request.FilterProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public interface IProductController {
    @PostMapping("/filter")
    Page<ProductDTO> findProductByCategory(@RequestBody FilterProductRequest request);

    @GetMapping("/{id}/{productId}")
    Product findProductById(@PathVariable Long productId) throws ProductException;

    @GetMapping("/skin/series")
    List<ProductDTO> findSkinProductBySeriesAndIdNot(@RequestParam String series, @RequestParam Long id) throws ProductException;

    @GetMapping("/champion/region")
    List<ProductDTO> findChampionProductBySeriesAndIdNot(@RequestParam String region, @RequestParam Long id) throws ProductException;

    @GetMapping("/chibi/champion")
    List<ProductDTO> findChibiProductBySeriesAndIdNot(@RequestParam String champion, @RequestParam Long id) throws ProductException;

    @GetMapping("/carousel")
    List<ProductCarouselSeriesDTO> findProductBySeries(@RequestParam String series) throws ProductException;

    @GetMapping("/series/all")
    List<String> getAllSeriesName(@RequestParam String category) throws ProductException;

    @GetMapping("/new")
    List<ProductSeries> getNewSkinProduct(@RequestParam String category) throws ProductException;
}

