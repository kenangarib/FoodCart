package com.ecommerce.library.service.impl;
import com.ecommerce.library.model.*;
import com.ecommerce.library.model.Order;
import com.ecommerce.library.repository.CartItemRepository;
import com.ecommerce.library.repository.OrderDetailRepository;
import com.ecommerce.library.repository.OrderRepository;
import com.ecommerce.library.repository.ShoppingCartRepository;
import com.ecommerce.library.service.OrderService;
import com.ecommerce.library.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private  ShoppingCartService shoppingCartService;

    EntityManager entityManager;

    EntityManagerFactory entityManagerFactory;



    @Override
    public Order saveOrder(ShoppingCart cart) {
       Order order = new Order();
       order.setOrderDate(new Date());
       order.setCustomer(cart.getCustomer());
       order.setTax(2);
       order.setTotalPrice(cart.getTotalPrices());
       order.setAccept(false);
       order.setPaymentMethod("Cash");
       order.setOrderStatus("Pending");
       order.setQuantity(cart.getTotalItems());
       List<OrderDetail> orderDetailList = new ArrayList<>();
       for (CartItem item : cart.getCartItem()) {
           OrderDetail orderDetail = new OrderDetail();
           orderDetail.setOrder(order);
           orderDetail.setProduct(item.getProduct());
           orderDetailRepository.save(orderDetail);
           orderDetailList.add(orderDetail);
       }
       order.setOrderDetailList(orderDetailList);


       shoppingCartService.deleteCartById(cart.getId());
       return orderRepository.save(order);
   }




    @Override
    public void acceptOrder(Long id) {
        Order order = orderRepository.getById(id);
        order.setDeliveryDate(new Date());
        order.setOrderStatus("SHIPPING");
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> findAll(String username) {
        return null;
    }

    @Override
    public List<Order> findALlOrders() {
        return null;
    }

}
