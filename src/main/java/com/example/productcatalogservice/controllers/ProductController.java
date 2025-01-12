package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.commons.AuthenticationCommons;
import com.example.productcatalogservice.exceptions.InvalidTokenException;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    AuthenticationCommons authenticationCommons;
    RestTemplate restTemplate;

    ProductController(@Qualifier("SelfStoreProductService") ProductService productService, AuthenticationCommons authenticationCommons, RestTemplate restTemplate) {
        this.authenticationCommons = authenticationCommons;
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    //Sample endpoint for testing
    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hello " + name;
    }

    // localhost:8080/products/10
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable("productId") Long productId) {

//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
//                "http://UserManagementService/users/10", String.class
//        );
        System.out.println("Request is received through API gateway");
        return productService.getProductById(productId);
    }
//
//    // localhost:8080/products
//    @GetMapping("/validate/{token}")
//    public ResponseEntity<List<Product>> getAllProducts(@PathVariable String token) throws InvalidTokenException {
//        // Validate the token using UserService
//        UserDto userDto =  authenticationCommons.validateToken(token);
//
//        if (userDto == null) {
//            throw new InvalidTokenException();
//        }
//
////        boolean isAdmin = false;
////        for (Role role: userDto.getRoles()){
////            if (role.equals("ADMIN")){
////                isAdmin = true;
////                break;
////            }
////        }
////
////        if (!isAdmin){
////            throw new NotAdminException("User is not Authorised as an Admin");
////        }
//
//        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
//    }

    //Paging and Sorting ===========================
    // localhost:8080/products
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam String sortBy) throws InvalidTokenException {
        return new ResponseEntity<>(productService.getAllProducts(pageNumber, pageSize, sortBy),HttpStatus.OK);
    }

    // create a Product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    //Partial update
    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product) {
        return null;
    }

    //Replace product
    @PutMapping()
    public Product replaceProduct(@RequestBody Product product) {
        return productService.replaceProduct(product);
    }

    @DeleteMapping("/{productId}")
    public boolean deleteProduct(@PathVariable("productId") Long productId) {
        return productService.deleteProduct(productId);
    }

//    public String
}
