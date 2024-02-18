package com.example.service.service;

import com.example.service.dto.ProductDTO;
import com.example.service.exception.ProductException;
import com.example.service.mapper.ProductMapper;
import com.example.service.model.Category;
import com.example.service.model.Color;
import com.example.service.model.Product;
import com.example.service.repository.CategoryRepository;
import com.example.service.repository.ProductRepository;
import com.example.service.request.CreateProductRequest;
import com.example.service.request.UpdateProductRequest;
import com.example.service.response.SeriesResponse;
import com.example.service.service.implementation.ProductService;
import com.example.service.service.implementation.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductImplementation implements ProductService {

    private ProductRepository productRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;

    public ProductImplementation(ProductRepository productRepository,
                                 UserService userService,
                                 CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }
    @Override
    public Product createProduct(CreateProductRequest req) {
        Category category = categoryRepository.findByName(req.getCategory());

        if (category == null) {
            Category level = new Category();
            level.setName(req.getCategory());

            category = categoryRepository.save(level);
        }


        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setTier(req.getTier());
        product.setImageTier(req.getImageTier());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice((int) (req.getPrice() - (req.getPrice() * ((float) req.getDiscountPercent()) /100 )));
        product.setDiscountPercent(req.getDiscountPercent());
        product.setImageUrl(req.getImageUrl());
        product.setSeries(req.getSeries());
        product.setPrice(req.getPrice());
        product.setReleaseDate(req.getReleaseDate());
        product.setCanBeLooted(req.getCanBeLooted());
        product.setInStore(req.getInStore());
        product.setTrailerLink(req.getTrailerLink());
        int quantity = 0;
        for (Color e :req.getColor()) {
            e.setProduct(product);
            product.getColor().add(e);
            quantity += e.getQuantity();
        }
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setCreateAt(LocalDateTime.now());

        System.out.println("Create Product Successfully");

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        product.getColor().clear();
        productRepository.delete(product);
        return "Product Deleted Successfully";
    }

    @Override
    public Product updateProduct(Long productId, UpdateProductRequest req) throws ProductException {
        Product product = findProductById(productId);

        product.setTitle(req.getTitle());
        product.setTier(req.getTier());
        product.setImageTier(req.getImageTier());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice((int) (req.getPrice() - (req.getPrice() * ((float) req.getDiscountPercent()) /100 )));
        product.setDiscountPercent(req.getDiscountPercent());
        product.setImageUrl(req.getImageUrl());
        product.setSeries(req.getSeries());
        product.setPrice(req.getPrice());
        product.setReleaseDate(req.getReleaseDate());
        product.setCanBeLooted(req.getCanBeLooted());
        product.setInStore(req.getInStore());
        product.setTrailerLink(req.getTrailerLink());
        product.setColor(req.getColor());
        int quantity = 0;
        for (Color e :req.getColor()) {
            quantity += e.getQuantity();
        }
        product.setQuantity(quantity);

        System.out.println("Update Product Successfully");

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent()) {
            System.out.println("Get Product By Id successfully");
            return opt.get();
        }
        throw new ProductException("Product not found with id - " + id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<ProductDTO> getAllProduct(String category, List<String> tier, Integer minPrice, Integer maxPrice,
                                          Integer minDiscount, String sort, String name, String series,
                                          String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageble = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice,
                                                                    minDiscount, sort, name, series);

        if (!tier.isEmpty()) {
            products = products.stream().filter(p -> tier.stream().anyMatch(c -> c.equalsIgnoreCase(p.getTier())))
                    .collect(Collectors.toList());
        }
        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            }
            else if (stock.equals("out_of_stock")) {
                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
            }
        }

        int startIndex = (int) pageble.getOffset();
        int endIndex = Math.min(startIndex + pageble.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);

        List<ProductDTO> productDTOS = ProductMapper.INSTANCE.mapList(pageContent);

        System.out.println("Get Product successfully");

        return new PageImpl<>(productDTOS, pageble, products.size());
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductBySeriesAndIdNot(String series, Long id) throws ProductException{
        return productRepository.findProductBySeriesAndIdNot(series, id);
    }

    @Override
    public List<Product> findProductBySeries(String series) throws ProductException {
        return productRepository.findProductBySeries(series);
    }

    @Override
    public List<String> findAllSeriesName() throws ProductException {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(Product::getSeries)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
