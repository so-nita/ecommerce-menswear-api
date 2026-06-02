package com.example.ecommerce_api.features.file.repository;

import com.example.ecommerce_api.features.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, String> {

}
