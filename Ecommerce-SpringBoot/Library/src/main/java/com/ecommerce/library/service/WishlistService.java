package com.ecommerce.library.service;

import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.model.Wishlist;
import org.springframework.stereotype.Service;

@Service
public interface WishlistService {
    Wishlist addItemToWish(Product product, Customer customer);

    Wishlist updateItemInCart(Product product, Customer customer);

    Wishlist deleteItemFromWish(Product product, Customer customer);

    void deleteWishById(Long id);
}


