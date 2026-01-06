package com.ecommerce.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private Integer quantity;
    private BigDecimal price;


}