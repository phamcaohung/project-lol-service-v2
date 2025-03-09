package com.example.service.repository;

import com.example.service.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ColorRepository extends JpaRepository<Color, Long> {
    Color findColorById(Long id);
}
