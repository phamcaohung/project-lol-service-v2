package com.example.service.dto;

public class ProductCarouselSeriesDTO {
    private Long id;

    private String imageUrl;

    private String imageTier;

    private String title;

    private String price;

    private String series;

    public ProductCarouselSeriesDTO() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTier() {
        return imageTier;
    }

    public void setImageTier(String imageTier) {
        this.imageTier = imageTier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
}
