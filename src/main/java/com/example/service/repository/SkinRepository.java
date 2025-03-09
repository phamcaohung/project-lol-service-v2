package com.example.service.repository;

import com.example.service.model.Skin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkinRepository extends JpaRepository<Skin, Long> {
    Skin findSkinById(Long id);

    List<Skin> findSkinBySeries(String series);
}
