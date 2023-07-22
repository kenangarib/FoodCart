package com.ecommerce.library.service.impl;

import com.ecommerce.library.model.*;
import com.ecommerce.library.repository.WishlistItemRepository;
import com.ecommerce.library.repository.WishlistRepository;
import com.ecommerce.library.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class WishlistServiceImpl implements WishlistService {


    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private WishlistItemRepository wishlistItemRepository;

    @Override
    public Wishlist addItemToWish(Product product, Customer customer) {
        Wishlist wishlist = customer.getWishlist();
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setCustomer(customer);
        }
        Set<WishlistItem> wishlistItems = wishlist.getItem();
        WishlistItem wishlistItem = findWishlistItem(wishlistItems, product.getId());
        if (wishlistItems == null) {
            wishlistItems = new HashSet<>();
            if (wishlistItem == null) {
                wishlistItem = new WishlistItem();
                wishlistItem.setProduct(product);
                wishlistItems.add(wishlistItem);
                wishlistItemRepository.save(wishlistItem);
            }
        } else {
            if (wishlistItem == null) {
                wishlistItem = new WishlistItem();
                wishlistItem.setProduct(product);
                wishlistItem.setWishlist(wishlist);
                wishlistItems.add(wishlistItem);
                wishlistItemRepository.save(wishlistItem);
            } else {
                wishlistItemRepository.save(wishlistItem);
            }
        }
        wishlist.setItem(wishlistItems);
        wishlist.setCustomer(customer);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist updateItemInCart(Product product, Customer customer) {

        Wishlist wishlist = customer.getWishlist();

        Set<WishlistItem> wishlistItems = wishlist.getItem();

        WishlistItem item = findWishlistItem(wishlistItems, product.getId());
        wishlistItemRepository.save(item);

        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist deleteItemFromWish(Product product, Customer customer) {
        Wishlist wishlist = customer.getWishlist();
        Set<WishlistItem> wishlistItems = wishlist.getItem();
        WishlistItem item = findWishlistItem(wishlistItems, product.getId());
        wishlistItems.remove(item);
        wishlistItemRepository.delete(item);
        wishlist.setItem(wishlistItems);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void deleteWishById(Long id) {
        Wishlist wishlist = wishlistRepository.getById(id);
        for (WishlistItem wishlistItem : wishlist.getItem()) {
            wishlistItemRepository.deleteById(wishlistItem.getId());
        }
        wishlist.setCustomer(null);
        wishlist.getItem().clear();
        wishlistRepository.save(wishlist);
    }


    private WishlistItem findWishlistItem(Set<WishlistItem> wishlistItems, Long productId) {
        if (wishlistItems == null) {
            return null;
        }
        WishlistItem wishlistItem = null;
        for (WishlistItem item : wishlistItems) {
            if (item.getProduct().getId() == productId) {
                wishlistItem = item;
            }
        }
        return wishlistItem;
    }
}
