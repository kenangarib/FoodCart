package com.ecommerce.customer.controller;

import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.model.Wishlist;
import com.ecommerce.library.service.CustomerService;
import com.ecommerce.library.service.ProductService;
import com.ecommerce.library.service.ShoppingCartService;
import com.ecommerce.library.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class WishlistController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private ProductService productService;

    @GetMapping("/wishlist")
    public String wishlist(Model model, Principal principal, HttpSession session) {
        if (principal == null) {
            return "redirect:/register";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            Wishlist wishlist = customer.getWishlist();
            if (wishlist == null) {
                model.addAttribute("check");

            }

            model.addAttribute("wishlist", wishlist);
            model.addAttribute("title", "wishlist");
            return "wishlist";
        }
    }


    @PostMapping("/add-to-wishlist")
    public String addItemToWishlist(
            @RequestParam("id") Long productId,
            Principal principal,
            Model model,
            HttpSession session,
            HttpServletRequest request) {

        if (principal == null) {
            return "redirect:/register";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            session.setAttribute("username", customer.getUsername());
            Wishlist wishlist = customer.getWishlist();

            Product product = productService.getProductById(productId);
            wishlistService.addItemToWish(product, customer);

            // Wishlist nesnesini güncelledikten sonra customer nesnesine de bu değişiklikleri aktar
            customer.setWishlist(wishlist);

            return "redirect:" + request.getHeader("Referer");
        }
    }


    @PostMapping(value = "/delete-product")
    private String deleteItemFromCart(@RequestParam("id") Long productId,
                                  Model model,
                                  Principal principal,
                                  HttpSession session) {
        if (principal == null) {
            return "redirect:/register";
        } else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            Wishlist wishlist = wishlistService.deleteItemFromWish(product,  customer);
            model.addAttribute("wishlist", wishlist);

            Customer customer1 = customerService.findByUsername(principal.getName());
            session.setAttribute("username", customer1.getUsername());
            Wishlist wishlist1 = customer1.getWishlist();
            return "redirect:/wishlist";
        }
    }

}
