package com.example.service.repository;

import com.example.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p " +
            "WHERE (p.category.name = :category OR :category = '') " +
            "AND (:name = '' OR p.title LIKE CONCAT('%', :name, '%')) " +
            "AND (:series = '' OR p.skin.series LIKE CONCAT('%', :series, '%')) " +
            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) " +
            "AND (:minDiscount IS NULL OR p.discountPercent >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = '' THEN p.releaseDate END DESC, " +
            "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC")
    List<Product> filterProducts(@Param("category") String category, @Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice, @Param("minDiscount") Integer minDiscount,
                                 @Param("sort") String sort, @Param("name") String name, @Param("series") String series);


    List<Product> findProductBySkin_SeriesAndIdNot(String series, Long id);

    List<Product> findProductBySkin_Series(String series);

    List<Product> findTop12ProductByCategoryNameOrderByReleaseDateDesc(String category);
}
