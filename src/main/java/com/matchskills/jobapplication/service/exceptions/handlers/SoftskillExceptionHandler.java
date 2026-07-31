package com.matchskills.jobapplication.service.exceptions.handlers;

import com.matchskills.jobapplication.service.exceptions.CustomErrorResponse;
import com.matchskills.jobapplication.service.exceptions.customs.softskills.SoftskillNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SoftskillExceptionHandler {

    @ExceptionHandler(SoftskillNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> softskillNotFoundException(SoftskillNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomErrorResponse(e.getMessage(), 404));
    }

}
