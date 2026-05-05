package com.ecommerce.ecommerce_product_service.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface StorageService {

    public String uploadFile(MultipartFile file);
    public InputStream getFile(String keyName);
}
