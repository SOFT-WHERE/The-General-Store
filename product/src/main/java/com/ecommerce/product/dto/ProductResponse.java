package com.ecommerce.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductResponse {

    private String id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Boolean active;
}
