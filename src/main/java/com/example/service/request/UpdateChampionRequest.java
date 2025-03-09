package com.example.service.request;

import com.example.service.model.Color;
import com.example.service.model.Skill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateChampionRequest {
    private String imageUrl;

    private String title;

    private Boolean canBeLooted;

    private Boolean inStore;

    private String description;

    private int discountPercent;

    private int price;

    private Date releaseDate;

    private String trailerLink;

    private String role;

    private String difficulty;

    private List<Color> color = new ArrayList<>();

    private List<Skill> skill = new ArrayList<>();

    public UpdateChampionRequest() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }
}
