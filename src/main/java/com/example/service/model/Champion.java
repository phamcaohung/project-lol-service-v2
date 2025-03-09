package com.example.service.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "champion")
public class Champion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    private String difficulty;

    private String region;

    @OneToMany(mappedBy = "champion", cascade = CascadeType.ALL)
    private List<Skill> skill = new ArrayList<>();

    public Champion() {
    }

    public Champion(Long id, String role, String difficulty, List<Skill> skill, String region) {
        this.id = id;
        this.role = role;
        this.difficulty = difficulty;
        this.skill = skill;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
