package com.example.service.request;

import com.example.service.model.Champion;
import com.example.service.model.Color;
import com.example.service.model.Skin;

import java.util.Date;
import java.util.List;

public class UpdateProductRequest {

    private String title;

    private Boolean canBeLooted;

    private Boolean inStore;

    private String description;

    private int discountPercent;

    private int price;

    private Date releaseDate;

    private String trailerLink;

    private List<Color> color;

    private Champion champion;

    private Skin skin;

    public UpdateProductRequest() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCanBeLooted() {
        return canBeLooted;
    }

    public void setCanBeLooted(Boolean canBeLooted) {
        this.canBeLooted = canBeLooted;
    }

    public Boolean getInStore() {
        return inStore;
    }

    public void setInStore(Boolean inStore) {
        this.inStore = inStore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
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
}
