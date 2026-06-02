package com.example.ecommerce_api.features.file.mapper;

import com.example.ecommerce_api.features.file.dto.FileResponse;
import com.example.ecommerce_api.features.file.entity.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileMapper {

    @Mapping(target = "originalName", source = "originName")
    @Mapping(target = "url", ignore = true)
    FileResponse toDto(File file);
}
