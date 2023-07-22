package com.ecommerce.customer.controller;

import com.ecommerce.library.dto.CustomerDto;
import com.ecommerce.library.model.City;
import com.ecommerce.library.model.Country;
import com.ecommerce.library.model.Customer;
import com.ecommerce.library.service.CityService;
import com.ecommerce.library.service.CountryService;
import com.ecommerce.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/account")
    private String accountHome(Model model, Principal principal){

        if (principal == null){
            return "redirect:/register";
        }
        String username = principal.getName();
        CustomerDto customer = customerService.getCustomer(username);
        List<Country> countryList = countryService.findAll();
        List<City> cities = cityService.findAll();
        model.addAttribute("customer", customer);
        model.addAttribute("cities", cities);
        model.addAttribute("countries", countryList);
        model.addAttribute("title", "Profile");
        model.addAttribute("page", "Profile");

        return "my-account";
    }

/*    @RequestMapping(value = "/update-info", method = {RequestMethod.GET, RequestMethod.PUT})
    private String updateCustomer(@ModelAttribute("customer")Customer customer,
                                  Model model,
                                  Principal principal,
                                  RedirectAttributes attributes){

        if (principal == null){
            return "redirect:/register";
        }

        Customer customerSaved = customerService.saveInfo(customer);
        attributes.addFlashAttribute("customerSaved", customerSaved);
        return "redirect:/account";
    }*/
}
