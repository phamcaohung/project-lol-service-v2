package com.example.service.request;

import com.example.service.model.Champion;
import com.example.service.model.Chibi;
import com.example.service.model.Skin;

import java.time.LocalDateTime;

public class AddItemRequest {
    private Long productId;

    private String name;

    private String imageUrl;

    private String color;

    private String imageColor;

    private String nameColor;

    private int quantity;

    private int price;

    private int discountedPrice;

    private int discountPercent;

    private String category;

    private Champion champion;

    private Skin skin;

    private Chibi chibi;

    private LocalDateTime createAt;

    public AddItemRequest() {

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageColor() {
        return imageColor;
    }

    public void setImageColor(String imageColor) {
        this.imageColor = imageColor;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Chibi getChibi() {
        return chibi;
    }

    public void setChibi(Chibi chibi) {
        this.chibi = chibi;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }
}
