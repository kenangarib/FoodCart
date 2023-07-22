package com.ecommerce.customer.controller;

import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.service.CustomerService;
import com.ecommerce.library.service.ProductService;
import com.ecommerce.library.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CartController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String cart(Model model, Principal principal, HttpSession session) {
        if (principal == null) {
            return "redirect:/register";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            if (cart == null) {
                model.addAttribute("check");

            }
            if (cart != null) {
                model.addAttribute("grandTotal", cart.getTotalPrices());
            }
            model.addAttribute("shoppingCart", cart);
            model.addAttribute("title", "Cart");
            return "cart";
        }
    }

    @PostMapping("/add-to-cart")
    public String addItemToCart(
            @RequestParam("id") Long productId,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") Integer quantity,
            Principal principal,
            Model model,
            HttpSession session,
            HttpServletRequest request) {

        if (principal == null) {
            return "redirect:/register";
        } else {
            Customer customer = customerService.findByUsername(principal.getName());
            session.setAttribute("username", customer.getUsername());
            ShoppingCart shoppingCart = customer.getShoppingCart();
            if (shoppingCart != null) {
                session.setAttribute("totalItems", shoppingCart.getTotalItems());
            }
        }

        Product product = productService.getProductById(productId);
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart cart = shoppingCartService.addItemToCart(product, quantity, customer);
        return "redirect:" + request.getHeader("Referer");


    }


    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
    private String updateCart(@RequestParam("quantity") Integer quantity,
                              @RequestParam("id") Long productId,
                              Model model,
                              Principal principal,
                              HttpSession session) {
        if (principal == null) {
            return "redirect:/register";
        } else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            ShoppingCart cart = shoppingCartService.updateItemInCart(product, quantity, customer);
            model.addAttribute("shoppingCart", cart);

            Customer customer1 = customerService.findByUsername(principal.getName());
            session.setAttribute("username", customer1.getUsername());
            ShoppingCart shoppingCart = customer1.getShoppingCart();
            if (shoppingCart != null) {
                session.setAttribute("totalItems", shoppingCart.getTotalItems());
            }
            return "redirect:/cart";
        }
    }

    @RequestMapping(value = "update-cart", method = RequestMethod.POST, params = "action=delete")
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
            ShoppingCart cart = shoppingCartService.deleteItemFromCart(product, customer);
            model.addAttribute("shoppingCart", cart);

            Customer customer1 = customerService.findByUsername(principal.getName());
            session.setAttribute("username", customer1.getUsername());
            ShoppingCart shoppingCart = customer1.getShoppingCart();
            if (shoppingCart != null) {
                session.setAttribute("totalItems", shoppingCart.getTotalItems());
            }
            return "redirect:/cart";
        }


    }
}
