package com.example.ecommerce_api.features.file.service;

import com.example.ecommerce_api.common.dto.ApiResponse;
import com.example.ecommerce_api.features.file.dto.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ApiResponse<FileResponse> getFile(String fileId);
    ApiResponse<FileResponse> uploadFile(MultipartFile file);
}
