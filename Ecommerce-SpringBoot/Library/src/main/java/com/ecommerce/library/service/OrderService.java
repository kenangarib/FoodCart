package com.ecommerce.library.service;

import com.ecommerce.library.model.Order;
import com.ecommerce.library.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Order saveOrder(ShoppingCart cart);

    void acceptOrder(Long id);

    void cancelOrder(Long id);

    List<Order> findAll(String username);

    List<Order> findALlOrders();


}
