package com.example.service.request;

import com.example.service.model.Color;

import java.util.Date;
import java.util.List;

public class CreateProductRequest {
    private String title;

    private String series;

    private String description;

    private int price;

    private int discountPercent;

    private String tier;

    private String imageTier;

    private List<Color> color;

    private String imageUrl;

    private String category;

    private Date releaseDate;

    private String trailerLink;

    private Boolean inStore;

    private Boolean canBeLooted;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getImageTier() {
        return imageTier;
    }

    public void setImageTier(String imageTier) {
        this.imageTier = imageTier;
    }

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public Boolean getInStore() {
        return inStore;
    }

    public void setInStore(Boolean inStore) {
        this.inStore = inStore;
    }

    public Boolean getCanBeLooted() {
        return canBeLooted;
    }

    public void setCanBeLooted(Boolean canBeLooted) {
        this.canBeLooted = canBeLooted;
    }
}
