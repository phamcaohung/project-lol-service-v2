package com.example.service.repository;

import com.example.service.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<Image, Long> {
    Image findImageByName(String name);
}
