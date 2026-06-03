package com.example.ecommerce_api.features.file.service.Impl;

import com.example.ecommerce_api.common.dto.ApiResponse;
import com.example.ecommerce_api.config.MinioConfig;
import com.example.ecommerce_api.features.file.dto.FileResponse;
import com.example.ecommerce_api.features.file.entity.File;
import com.example.ecommerce_api.features.file.mapper.FileMapper;
import com.example.ecommerce_api.features.file.repository.FileRepository;
import com.example.ecommerce_api.features.file.service.FileService;
import com.example.ecommerce_api.features.user.service.CurrentUserService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final MinioClient minioClient;
    private final MinioConfig minioConfig;
    private final CurrentUserService currentUserService;

    @Override
    public ApiResponse<FileResponse> getFile(String fileId) {
        File file = fileRepository.findById(fileId).orElse(null);
        if (file == null) {
            return ApiResponse.NotFound("File not found");
        }
        FileResponse response = fileMapper.toDto(file);
        response.setUrl(buildUrl(file.getGeneratedName()));
        return ApiResponse.OK(response);
    }

    @Override
    public ApiResponse<FileResponse> uploadFile(MultipartFile multipartFile) {
        try {
            String originalName = multipartFile.getOriginalFilename();
            String extension = extractExtension(originalName);
            String generatedName = UUID.randomUUID() + (extension.isEmpty() ? "" : "." + extension);

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioConfig.getBucket())
                    .object(generatedName)
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .contentType(multipartFile.getContentType())
                    .build());

            var currentUser = currentUserService.get();

            File file = File.builder()
                    .originName(originalName)
                    .generatedName(generatedName)
                    .extension(extension)
                    .contentType(multipartFile.getContentType())
                    .createdBy(currentUser)
                    .build();

            file = fileRepository.save(file);

            FileResponse response = fileMapper.toDto(file);
            response.setUrl(buildUrl(generatedName));
            return ApiResponse.Created(response, "File uploaded");
        } catch (Exception e) {
            return ApiResponse.InternalServerError("Failed to upload file: " + e.getMessage());
        }
    }

    private String buildUrl(String objectName) {
        return minioConfig.getEndpoint() + "/" + minioConfig.getBucket() + "/" + objectName;
    }

    private String extractExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "";
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
