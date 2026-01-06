package com.ecommerce.product.service;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product p=new Product();
        updateProductFromRequest(p, productRequest);
        Product saved=productRepository.save(p);
        return mapToProductResponse(saved);
    }

    private ProductResponse mapToProductResponse(Product saved) {
        ProductResponse p=new ProductResponse();
        p.setId(String.valueOf(saved.getId()));
        p.setName(saved.getName());
        p.setStock(saved.getStock());
        p.setPrice(saved.getPrice());
        p.setActive(saved.getActive());
        return p;
    }

    private void updateProductFromRequest(Product p, ProductRequest productRequest) {
        Product temp=new Product();
//        p.setId(Long.valueOf(productRequest.getId()));
        if(productRequest.getName()!=null){
            p.setName(productRequest.getName());
        }
        p.setPrice(productRequest.getPrice());
        p.setStock(productRequest.getStock());
//        p.setActive(productRequest.getActive());
    }

    public List<ProductResponse> getAllProduct() {
        return productRepository.findByActiveTrue().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
        Optional<Product> temp=productRepository.findById(id);
        if(temp.isPresent()){
            Product p=temp.get();
            updateProductFromRequest(p,productRequest);
            Product saved=productRepository.save(p);
            ProductResponse pr=mapToProductResponse(saved);
            return Optional.of(pr);
        }
        return Optional.empty();
    }

    public boolean deleteProduct(Long id) {

        return productRepository.findById(id)
                .map(p->{
                    p.setActive(false);
                    productRepository.save(p);
                    return true;
                }).orElse(false);

    }

    public List<ProductResponse> searchProduct(String keyword) {

        return productRepository.searchProduct(keyword).stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }
}
