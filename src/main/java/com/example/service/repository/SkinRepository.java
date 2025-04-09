package com.example.service.repository;

import com.example.service.model.Skin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkinRepository extends JpaRepository<Skin, Long> {
    @Query("SELECT DISTINCT series FROM Skin")
    List<String> findDistinctSeries();
}
