package com.example.productcatalogservice.dtos;

import com.example.productcatalogservice.models.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends BaseModel {
    private String roleName;
}
