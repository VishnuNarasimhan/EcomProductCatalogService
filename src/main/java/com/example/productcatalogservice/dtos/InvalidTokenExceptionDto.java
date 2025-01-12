package com.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidTokenExceptionDto {
    private String message;
    private String detail;
}
