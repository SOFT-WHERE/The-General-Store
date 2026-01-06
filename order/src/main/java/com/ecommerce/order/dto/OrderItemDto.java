package com.ecommerce.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long id;
    private String productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;

}
