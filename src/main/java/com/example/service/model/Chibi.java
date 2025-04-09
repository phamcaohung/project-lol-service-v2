package com.example.service.model;


import jakarta.persistence.*;

@Entity
@Table(name = "chibi")
public class Chibi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tier;

    private String imageTier;

    private String champion;

    public Chibi() {}

    public Chibi(Long id, String tier, String imageTier, String champion) {
        this.id = id;
        this.tier = tier;
        this.imageTier = imageTier;
        this.champion = champion;
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

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }
}
