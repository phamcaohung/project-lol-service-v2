package com.example.service.repository;

import com.example.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p " +
            "LEFT JOIN p.chibi c " +
            "LEFT JOIN p.skin s " +
            "LEFT JOIN p.champion a " +
            "WHERE p.category.name = :category " +
            "AND (:title = '' OR p.title LIKE CONCAT('%', :title, '%')) " +
            "AND (:series = '' OR s.series LIKE CONCAT('%', :series, '%')) " +
            "AND (:champion = '' OR c.champion LIKE CONCAT('%', :champion, '%')) " +
            "AND (:region = '' OR a.region LIKE CONCAT('%', :region, '%')) " +
            "AND (:role = '' OR a.role LIKE CONCAT('%', :role, '%')) " +
            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) " +
            "AND (:minDiscount IS NULL OR p.discountPercent >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'title_low' THEN p.title END DESC, " +
            "CASE WHEN :sort = 'title_high' THEN p.title END ASC, " +
            "CASE WHEN :sort = 'releaseDate_low' THEN p.releaseDate END DESC, " +
            "CASE WHEN :sort = 'releaseDate_high' THEN p.releaseDate END ASC, " +
            "CASE WHEN :sort = 'quantity_low' THEN p.quantity END DESC, " +
            "CASE WHEN :sort = 'quantity_high' THEN p.quantity END ASC, " +
            "CASE WHEN :sort = 'price_low' THEN p.price END DESC, " +
            "CASE WHEN :sort = 'price_high' THEN p.price END ASC")
    List<Product> filterProducts(@Param("category") String category, @Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice, @Param("minDiscount") Integer minDiscount,
                                 @Param("sort") String sort, @Param("title") String title,
                                 @Param("series") String series, @Param("champion") String champion,
                                 @Param("region") String region, @Param("role") String role);

    List<Product> findProductBySkin_SeriesAndIdNot(String series, Long id);

    List<Product> findProductByChampion_RegionAndIdNot(String region, Long id);

    List<Product> findProductByChibi_ChampionAndIdNot(String champion, Long id);

    List<Product> findProductBySkin_Series(String series);

    List<Product> findTop12ProductByCategoryNameOrderByReleaseDateDesc(String category);
}
