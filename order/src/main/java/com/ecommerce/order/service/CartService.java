package com.ecommerce.order.service;

import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.model.CartItem;
//import com.app.ecomm.model.Product;
//import com.app.ecomm.model.User;
import com.ecommerce.order.repository.CartItemRepository;
//import com.app.ecomm.repository.ProductRepository;
//import com.app.ecomm.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
//    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
//    private final UserRepository userRepository;

    public boolean addToCart(String userId, CartItemRequest request) {

//        Optional<Product> productOpt=productRepository.findById(request.getProductId());
//        if(productOpt.isEmpty()){
//            return false;
//        }
//        Product product=productOpt.get();
//        if(product.getStock()< request.getQuantity()){
//            return false;
//        }
//
//        Optional<User> userOpt=userRepository.findById(Long.valueOf(userId));
//        if(userOpt.isEmpty()){
//            return false;
//        }
//        User user=userOpt.get();

        CartItem exsitingCartItem=cartItemRepository.findByUserIdAndProductId(userId, request.getProductId());
        if(exsitingCartItem!=null){
            //update quantity
            exsitingCartItem.setQuantity(exsitingCartItem.getQuantity()+ request.getQuantity());
            exsitingCartItem.setPrice(BigDecimal.valueOf(1000));
            cartItemRepository.save(exsitingCartItem);
        }
        else{
            //create cart item
            CartItem cartItem=new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(BigDecimal.valueOf(1000));
            cartItemRepository.save(cartItem);
        }
        return true;
    }


    public boolean deleteItemFromCart(String userId, Long productId) {
        CartItem cartItem=cartItemRepository.findByUserIdAndProductId(userId, String.valueOf(productId));
        if(cartItem!=null){
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    public List<CartItem> getCartItem(String userId) {

        return cartItemRepository.findByUserId(userId);
    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
