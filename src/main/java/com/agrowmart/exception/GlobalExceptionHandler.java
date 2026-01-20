package com.agrowmart.exception;

import com.agrowmart.admin_seller_management.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(
                new ApiResponseDTO<>(
                        false,
                        "Validation failed",
                        null,
                        errors
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleIllegalArgument(
            IllegalArgumentException ex) {

        return ResponseEntity.badRequest().body(
                new ApiResponseDTO<>(false, ex.getMessage())
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleRuntime(
            RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponseDTO<>(false, ex.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<?>> handleGeneric(
            Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponseDTO<>(false, "Something went wrong")
        );
    }
}
