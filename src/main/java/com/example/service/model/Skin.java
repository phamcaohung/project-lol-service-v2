package com.example.service.model;

import jakarta.persistence.*;


@Entity
@Table(name = "skin")
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tier;

    private String imageTier;

    private String series;

    public Skin() {
    }

    public Skin(Long id, String tier, String imageTier, String series) {
        this.id = id;
        this.tier = tier;
        this.imageTier = imageTier;
        this.series = series;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
}
