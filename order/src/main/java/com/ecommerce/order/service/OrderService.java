package com.ecommerce.order.service;

import com.ecommerce.order.dto.OrderItemDto;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.model.*;
import com.ecommerce.order.repository.CartItemRepository;
import com.ecommerce.order.repository.OrderRepository;
//import com.app.ecomm.repository.UserRepository;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.OrderItem;
import com.ecommerce.order.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartItemRepository cartItemRepository;
    private final CartService cartService;
    private final OrderRepository orderRepository;

//    public OrderService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public Optional<OrderResponse> createOrder(String userId) {
        //validate user
//        Optional<User> optUser=userRepository.findById(Long.valueOf(userId));
//        if(optUser.isEmpty()){
//            return Optional.empty();
//        }
//        User user=optUser.get();
        //validate cart items
        List<CartItem> CartItems=cartService.getCartItem(userId);
        if(CartItems.isEmpty()){
            return Optional.empty();
        }
        //calculate total amount
        BigDecimal total= CartItems.stream().map(item->item.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //create order
        Order order=new Order();
        order.setUserId(userId);

        order.setAmount(total);
        order.setStatus(OrderStatus.COMPLETED);
        List<OrderItem> orderItems= CartItems.stream().map(item->
                new OrderItem(
                        null,
                        item.getProductId(),
                        order,
                        item.getQuantity(),
                        item.getPrice()
                )).toList();

        order.setItems(orderItems);
        Order savedOrder=orderRepository.save(order);
        //clear cart
        cartService.clearCart(userId);

    return Optional.of(mapToOrderResponse(savedOrder));


    }

    private OrderResponse mapToOrderResponse(Order savedOrder) {

        OrderResponse or=new OrderResponse();
        or.setId(savedOrder.getId());
        or.setStatus(savedOrder.getStatus());
        or.setAmount(savedOrder.getAmount());
        or.setItems(savedOrder.getItems().stream()
                .map(orderItem -> new OrderItemDto(
                        orderItem.getId(),
                        orderItem.getProductId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice(),
                        orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
                )).toList());
                or.setCreatedAt(savedOrder.getCreatedAt());


        return or;
    }
}
