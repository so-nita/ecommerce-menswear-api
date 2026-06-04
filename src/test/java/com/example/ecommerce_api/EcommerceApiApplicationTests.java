package com.example.ecommerce_api;

import io.minio.MinioClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class EcommerceApiApplicationTests {

	@MockitoBean
	MinioClient minioClient;

	@Test
	void contextLoads() {
	}

}
