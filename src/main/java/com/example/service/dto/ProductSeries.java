package com.example.service.dto;

import com.example.service.model.Color;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProductSeries {
    private Long id;

    private String imageUrl;

    private String imageTier;

    private String title;

    private String price;

    private String series;

    private Date ReleaseDate;

    private Set<Color> color = new HashSet<>();

    public ProductSeries() {

    }

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

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }

    public Set<Color> getColor() {
        return color;
    }

    public void setColor(Set<Color> color) {
        this.color = color;
    }
}
