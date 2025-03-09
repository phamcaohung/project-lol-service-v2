package com.example.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    private String name;

    private int number;

    private String image;

    @Column(length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "champion_id")
    @JsonIgnore
    private Champion champion;

    public Skill() {
    }

    public Skill(Long id, String name, String image, String description, Champion champion, int number) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.champion = champion;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
