package com.example.productcatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogServiceApplication.class, args);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$ APPLICATION STARTED SUCCESSFULLY $$$$$$$$$$$$$$$$$$$$$");
        System.out.println("PORT: http://localhost:7070");
    }

}
