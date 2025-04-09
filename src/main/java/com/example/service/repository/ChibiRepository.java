package com.example.service.repository;

import com.example.service.model.Chibi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChibiRepository extends JpaRepository<Chibi, Long> {
    @Query("SELECT DISTINCT champion FROM Chibi")
    List<String> findDistinctChampion();
}
