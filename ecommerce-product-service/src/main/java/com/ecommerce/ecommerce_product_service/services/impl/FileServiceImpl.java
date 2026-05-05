package com.ecommerce.ecommerce_product_service.services.impl;
import com.ecommerce.ecommerce_product_service.services.StorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileServiceImpl {
    private final StorageService storageService;

    public FileServiceImpl(@Qualifier("localStorageService") StorageService storageService){
        this.storageService=storageService;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        return storageService.uploadFile(file);

    }

    public InputStream getFile(String keyName) throws FileNotFoundException {
        return  storageService.getFile(keyName);
    }
}
