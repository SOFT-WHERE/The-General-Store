package com.ecommerce.product.controller;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
        ProductResponse pr=productService.addProduct(productRequest);
        return ResponseEntity.ok(pr);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        List<ProductResponse> pr=productService.getAllProduct();
        return ResponseEntity.ok(pr);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> updateProduct(@PathVariable Long id,
            @RequestBody ProductRequest productRequest){
        Optional<ProductResponse> pr=productService.updateProduct(id, productRequest);
        return ResponseEntity.ok(pr);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        boolean deleted=productService.deleteProduct(id);
        return deleted?ResponseEntity.noContent().build():ResponseEntity.notFound().build();

    }

    @GetMapping("search")
    public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam String keyword){
        return ResponseEntity.ok(productService.searchProduct(keyword));
    }

}
