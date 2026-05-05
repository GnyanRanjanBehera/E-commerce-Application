package com.ecommerce.ecommerce_product_service.services.impl;

import com.ecommerce.ecommerce_product_service.services.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

@Service("s3StorageService")
public class S3StorageServiceImpl implements StorageService {
    @Override
    public String uploadFile(MultipartFile file) {
        return "";
    }

    @Override
    public InputStream getFile(String keyName) {
        return null;
    }
}
