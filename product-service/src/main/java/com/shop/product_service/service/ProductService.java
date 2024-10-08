package com.shop.product_service.service;

import com.shop.product_service.dto.ProductDto;
import com.shop.product_service.entity.Product;
import com.shop.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final RedissonClient redissonClient;

    public List<Product> getProductList() {
        List<Product> productList = productRepository.findAll();
        List<Product> newProductList = new ArrayList<>();
        newProductList.addAll(productList);
        return newProductList;
    }

    @Cacheable(value = "PRODUCT" , cacheManager = "cacheManager")
    public Product getProduct(Long productId) {
        return productRepository.findProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product with ID " + productId + " not found"));
    }

    @Transactional
    public void decreaseStock(Long productId, int quantity) {
        RLock lock = redissonClient.getLock(productId.toString());
        try {
            boolean isLocked = lock.tryLock(1000, 30, TimeUnit.SECONDS);
            if (isLocked) {
                Product product = productRepository.findProductById(productId).orElseThrow(RuntimeException::new);
                product.removeStock(quantity);
                productRepository.save(product);
            } else {
                System.out.println("상품 재고 수정 중입니다. --- (DB Locked)");
                return;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted" + e);
        } finally {
            lock.unlock();
        }
    }

    @Cacheable(value = "PRODUCT" , cacheManager = "cacheManager")
    public Product saveProduct(ProductDto productDto) {
        String productName = productDto.getProductName();
        String description = productDto.getDescription();
        String category = productDto.getCategory();
        int productCount = productDto.getProductCount();
        int price = productDto.getPrice();
        String productImg = productDto.getProductImg();

        Product product = new Product(productDto);

        return productRepository.save(product);
    }


    @CacheEvict(value = "PRODUCT" , cacheManager = "cacheManager")
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }


}