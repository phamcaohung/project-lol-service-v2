package com.example.service.service;

import com.example.service.exception.ProductException;
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

    private final String FILE_PATH_IMAGE_MAIN = "C:\\Users\\ADMIN\\Desktop\\Projects\\lol\\service_v2\\service\\src\\main\\resources\\public\\image\\image_main\\";
    private final String URL_IMAGE_MAIN = "http://localhost:8080/image/image_main/";

    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Override
    public Image storeFileUploadIntoFileSystem(MultipartFile file) throws IOException {
        String filePathImageMain = FILE_PATH_IMAGE_MAIN + file.getOriginalFilename();
        String urlImageMain = URL_IMAGE_MAIN + file.getOriginalFilename();

        Image files = new Image();
        files.setName(file.getOriginalFilename());
        files.setPath(filePathImageMain);
        files.setType(file.getContentType());
        files.setUrl(urlImageMain);

        fileUploadRepository.save(files);

        file.transferTo(new File(filePathImageMain));

        return files;
    }

    @Override
    public Image findImageByName(String name) throws ProductException {
        return fileUploadRepository.findImageByName(name);
    }
}
