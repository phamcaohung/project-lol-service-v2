package com.example.service.repository;

import com.example.service.model.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionRepository extends JpaRepository<Champion, Long> {
    Champion findChampionById(Long id);
}
