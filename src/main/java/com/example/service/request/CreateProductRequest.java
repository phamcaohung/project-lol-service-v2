package com.example.service.request;

import com.example.service.model.*;

import java.util.Date;
import java.util.List;

public class CreateProductRequest {
    private String title;

    private String description;

    private int price;

    private int discountPercent;

    private String category;

    private Date releaseDate;

    private String trailerLink;

    private Boolean inStore;

    private Boolean canBeLooted;

    private List<Color> color;

    private Champion champion;

    private Skin skin;

    private Chibi chibi;

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

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
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
}
