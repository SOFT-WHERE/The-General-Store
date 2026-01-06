package com.ecommerce.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductRequest {


    private String name;
    private BigDecimal price;
    private Integer stock;

}
