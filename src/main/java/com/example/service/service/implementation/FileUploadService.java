package com.example.service.service.implementation;

import com.example.service.exception.ProductException;
import com.example.service.model.Category;
import com.example.service.model.Image;
import com.example.service.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    public Image storeImageUploadIntoFileSystem(MultipartFile file, Category category) throws IOException;

    public Image findImageByName(String name) throws ProductException;
}
