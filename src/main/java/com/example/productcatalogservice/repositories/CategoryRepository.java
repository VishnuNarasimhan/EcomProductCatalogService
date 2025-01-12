package com.example.productcatalogservice.repositories;

import com.example.productcatalogservice.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
