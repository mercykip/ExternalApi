package com.example.httprequest.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class JsonResponse {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> onBadRequest(BadRequestException badRequestException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();
    }

}
