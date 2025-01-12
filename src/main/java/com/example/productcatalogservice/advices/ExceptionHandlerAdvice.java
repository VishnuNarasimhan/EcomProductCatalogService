package com.example.productcatalogservice.advices;

import com.example.productcatalogservice.dtos.InvalidTokenExceptionDto;
import com.example.productcatalogservice.exceptions.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<InvalidTokenExceptionDto> handleInvalidTokenException() {
        InvalidTokenExceptionDto invalidTokenExceptionDto = new InvalidTokenExceptionDto();
        invalidTokenExceptionDto.setMessage("Invalid Token Passed");
        invalidTokenExceptionDto.setDetail("Try to Login Again and Retry");
        return new ResponseEntity<>(invalidTokenExceptionDto, HttpStatus.UNAUTHORIZED);
    }
}
