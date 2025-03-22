package com.megacity.server.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
public class CloudinaryService {

    private static final Logger logger = Logger.getLogger(CloudinaryService.class.getName());

    private final Cloudinary cloudinary;

    public CloudinaryService(@Value("${cloudinary.cloud-name}") String cloudName,
                             @Value("${cloudinary.api-key}") String apiKey,
                             @Value("${cloudinary.api-secret}") String apiSecret) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    @Async
    public CompletableFuture<String> uploadImage(MultipartFile file) throws IOException {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("File is empty");
            }

            @SuppressWarnings("rawtypes")
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = uploadResult.get("url").toString();
            logger.info("Image uploaded successfully: " + imageUrl);
            return CompletableFuture.completedFuture(imageUrl);
        } catch (IOException e) {
            logger.severe("Failed to upload image: " + e.getMessage());
            throw e;
        }
    }
}