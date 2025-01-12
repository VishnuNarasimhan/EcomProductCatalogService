package com.example.productcatalogservice.repositories;

import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Override
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Long id);

    Optional<Product> findByTitleAndDescription(String title, String description);

    Optional<Product> findByTitleContaining(String title);
    // Using Like Operator

    List<Product> findTopThreeByTitle(String title);
    // Limit the result by 3

    List<Product> findByCategory(Category category);
    
    void deleteById(Long id);
    
    void deleteByTitle(String title);
}
