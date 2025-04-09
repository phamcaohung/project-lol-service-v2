package com.example.service.request;

import com.example.service.model.Champion;
import com.example.service.model.Chibi;
import com.example.service.model.Skin;

public class FilterProductRequest {
    private String category;

    private String title;

    private Integer minPrice;

    private Integer maxPrice;

    private Integer minDiscount;

    private String sort;

    private String stock;

    private Integer pageNumber;

    private Integer pageSize;

    private Skin skin;

    private Champion champion;

    private Chibi chibi;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinDiscount() {
        return minDiscount;
    }

    public void setMinDiscount(Integer minDiscount) {
        this.minDiscount = minDiscount;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public Chibi getChibi() {
        return chibi;
    }

    public void setChibi(Chibi chibi) {
        this.chibi = chibi;
    }
}
