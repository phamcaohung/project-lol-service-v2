package com.example.service.service;

import com.example.service.exception.ProductException;
import com.example.service.model.Category;
import com.example.service.model.Image;
import com.example.service.model.Product;
import com.example.service.repository.FileUploadRepository;
import com.example.service.service.implementation.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadImplementation implements FileUploadService {

    private final String FILE_PATH_IMAGE_SKIN = "C:\\Users\\ADMIN\\Desktop\\Projects\\lol\\service_v2\\service\\src\\main\\resources\\public\\image\\image_skin\\";
    private final String FILE_PATH_IMAGE_CHAMPION = "C:\\Users\\ADMIN\\Desktop\\Projects\\lol\\service_v2\\service\\src\\main\\resources\\public\\image\\image_champion\\";
    private final String FILE_PATH_IMAGE_CHiBI = "C:\\Users\\ADMIN\\Desktop\\Projects\\lol\\service_v2\\service\\src\\main\\resources\\public\\image\\image_chibi\\";

    private final String URL_IMAGE_SKIN = "http://localhost:8080/image/image_skin/";
    private final String URL_IMAGE_CHAMPION = "http://localhost:8080/image/image_champion/";
    private final String URL_IMAGE_CHIBI = "http://localhost:8080/image/image_chibi/";

    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Override
    public Image storeImageUploadIntoFileSystem(MultipartFile file, Category category) throws IOException {
        String filePathImageSkin = FILE_PATH_IMAGE_SKIN + file.getOriginalFilename();
        String urlImageSkin = URL_IMAGE_SKIN + file.getOriginalFilename();
        String filePathImageChampion = FILE_PATH_IMAGE_CHAMPION + file.getOriginalFilename();
        String urlImageChampion = URL_IMAGE_CHAMPION + file.getOriginalFilename();
        String filePathImageChibi = FILE_PATH_IMAGE_CHiBI + file.getOriginalFilename();
        String urlImageChibi = URL_IMAGE_CHIBI + file.getOriginalFilename();

        Image files = new Image();
        switch (category.getName()) {
            case "skin" -> {
                file.transferTo(new File(filePathImageSkin));
                files.setPath(filePathImageSkin);
                files.setUrl(urlImageSkin);
            }
            case "champion" -> {
                file.transferTo(new File(filePathImageChampion));
                files.setPath(filePathImageChampion);
                files.setUrl(urlImageChampion);
            }
            case "chibi" -> {
                file.transferTo(new File(filePathImageChibi));
                files.setPath(filePathImageChibi);
                files.setUrl(urlImageChibi);
            }
        }
        files.setName(file.getOriginalFilename());
        files.setType(file.getContentType());

        fileUploadRepository.save(files);
        return files;
    }

    @Override
    public Image findImageByName(String name) throws ProductException {
        return fileUploadRepository.findImageByName(name);
    }
}
