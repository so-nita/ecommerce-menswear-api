package com.example.ecommerce_api.common.dto;

import com.example.ecommerce_api.constant.StatusCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final int statusCode;
    private final String message;
    private final T data;

    public static <T> ApiResponse<T> OK (T data){
        return ApiResponse.<T>builder()
                .statusCode(StatusCode.SUCCESS)
                .message("Successfully")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> Created (T data, String message) {
        return ApiResponse.<T>builder()
                .statusCode(201)
                .message("Created")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> NotFound(String message) {
        return ApiResponse.<T>builder()
                .statusCode(StatusCode.NOT_FOUND)
                .message(message)
                .data(null)
                .build();
    }

    public static <T> ApiResponse<T> BadRequest(String message) {
        return ApiResponse.<T>builder()
                .statusCode(StatusCode.BAD_REQUEST)
                .message(message)
                .data(null)
                .build();
    }

    public static <T> ApiResponse<T> Unauthorized(String message) {
        return ApiResponse.<T>builder()
                .statusCode(StatusCode.UNAUTHORIZED)
                .message(message)
                .data(null)
                .build();
    }

    public static <T> ApiResponse<T> Forbidden(String message) {
        return ApiResponse.<T>builder()
                .statusCode(StatusCode.FORBIDDEN)
                .message(message)
                .data(null)
                .build();
    }

    public static <T> ApiResponse<T> MethodNotAllowed(String message) {
        return ApiResponse.<T>builder()
                .statusCode(StatusCode.METHOD_NOT_ALLOWED)
                .message(message)
                .data(null)
                .build();
    }

    public static <T> ApiResponse<T> InternalServerError(String message) {
        return ApiResponse.<T>builder()
                .statusCode(StatusCode.INTERNAL_SERVER_ERROR)
                .message(message)
                .data(null)
                .build();
    }
}
