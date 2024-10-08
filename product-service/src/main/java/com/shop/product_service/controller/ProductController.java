package com.shop.product_service.controller;

import com.shop.product_service.dto.ProductDto;
import com.shop.product_service.entity.Product;
import com.shop.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-service")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> productList = productService.getProductList();
        return ResponseEntity.ok(productList);

    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
        Product product = productService.getProduct(productId);
        return ResponseEntity.ok(product);
    }


    @PostMapping("/register")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDto productDto) {
        Product product= productService.saveProduct(productDto);
        Long productId = product.getId();
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product Deleted");
    }
}