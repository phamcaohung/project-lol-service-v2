package com.example.service.repository;

import com.example.service.model.Cart;
import com.example.service.model.CartItem;
import com.example.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query(
            "SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.product = :product AND ci.nameColor = :nameColor"
    )
    CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product, @Param("nameColor") String nameColor);

    void deleteCartItemByCart(Cart cart);
}
