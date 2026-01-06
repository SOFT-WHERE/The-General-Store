package com.ecommerce.order.dto;

import com.ecommerce.order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private BigDecimal amount;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private LocalDateTime createdAt;



}
