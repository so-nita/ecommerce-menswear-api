package com.example.ecommerce_api.features.file.controller;

import com.example.ecommerce_api.common.dto.ApiResponse;
import com.example.ecommerce_api.features.file.dto.FileResponse;
import com.example.ecommerce_api.features.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<FileResponse>> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(fileService.uploadFile(file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FileResponse>> getById(@PathVariable String id) {
        return ResponseEntity.ok(fileService.getFile(id));
    }
}
