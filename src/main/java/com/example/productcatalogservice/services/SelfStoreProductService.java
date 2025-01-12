package com.example.productcatalogservice.services;

import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repositories.CategoryRepository;
import com.example.productcatalogservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("SelfStoreProductService")
public class SelfStoreProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    SelfStoreProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            // Throw Exception
            return null;
        }

        return product.get();
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortBy) {
        // Pagination Implementation
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize,
                sortBy.equalsIgnoreCase("asc") ? Sort.by("price").ascending() :
                        Sort.by("price").descending()));
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();

        if (category.getId() == null) {
            // first save the category in the DB
            categoryRepository.save(category);
            product.setCategory(category);
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct() {
        return null;
    }

    @Override
    public Product replaceProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product> OptionalProduct = productRepository.findById(id);
        if (OptionalProduct.isEmpty()) {
            return false;
        }
        productRepository.delete(OptionalProduct.get());
        return true;

    }
}
