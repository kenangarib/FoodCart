package com.ecommerce.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@Entity @Table(name = "shopping_cart", schema = "shopcart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long id;
    private int totalItems;
    private double totalPrices;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private Set<CartItem> cartItem;


    public ShoppingCart() {
        this.cartItem = new HashSet<>();
        this.totalItems = 0;
        this.totalPrices = 0.0;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", customer=" + customer.getUsername() +
                ", totalPrice=" + totalPrices +
                ", totalItems=" + totalItems +
                ", cartItems=" + cartItem.size() +
                '}';
    }
}
