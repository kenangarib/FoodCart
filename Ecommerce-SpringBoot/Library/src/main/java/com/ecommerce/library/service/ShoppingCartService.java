package com.ecommerce.library.service;

import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartService {

    ShoppingCart addItemToCart(Product product, Integer quantity, Customer customer);

    ShoppingCart updateItemInCart(Product product, Integer quantity, Customer customer);

    ShoppingCart deleteItemFromCart(Product product, Customer customer);

    void deleteCartById(Long id);
}
