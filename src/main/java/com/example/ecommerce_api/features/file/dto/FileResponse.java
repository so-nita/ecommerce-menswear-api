package com.example.ecommerce_api.features.file.dto;

import lombok.Data;

@Data
public class FileResponse {
    private String id;
    private String originalName;
    private String generatedName;
    private String url;
}
