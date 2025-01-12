package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.FakeStoreProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService {
    RestTemplate restTemplate;
    RedisTemplate redisTemplate;

    FakeStoreProductServiceImpl(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public Product getProductById(Long id) {

        // Check in Redis Cache first

        // Here key is the map name in Redis
        // hashKey is the key in the map PRODUCTS in Redis
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_" + id);

        if (product != null) {
            // Cache Hit
            return product;
        }

        // Call the FakeStoreAPI to get the product with given ID here.
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if (fakeStoreProductDto == null) {
            return null;
        }

        // Convert FakeStoreProductDto to product object.
        product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

        // Here key - PRODUCTS is the map name in Redis
        // hashKey is the key in the map PRODUCTS in Redis
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_" + id, product);

        // Convert FakeStoreProductDto to product object.
        return product;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sort) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct() {
        return null;
    }

    @Override
    public Product replaceProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return true;
    }
}
