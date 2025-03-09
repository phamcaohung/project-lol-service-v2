package com.example.service.request;


public class CreateSkinRequest {
    private String tier;

    private String imageTier;

    private String series;

    private String description;

    private ProductRequest productRequest;

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

    public ProductRequest getProductRequest() {
        return productRequest;
    }

    public void setProductRequest(ProductRequest productRequest) {
        this.productRequest = productRequest;
    }
}
