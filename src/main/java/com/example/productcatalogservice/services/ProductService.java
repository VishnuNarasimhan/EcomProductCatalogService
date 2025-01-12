package com.example.productcatalogservice.services;

import com.example.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);

    Page<Product> getAllProducts(int pageNumber, int pageSize, String sortBy);

    Product createProduct(Product product);

    Product updateProduct();

    Product replaceProduct(Product product);

    boolean deleteProduct(Long id);
}
