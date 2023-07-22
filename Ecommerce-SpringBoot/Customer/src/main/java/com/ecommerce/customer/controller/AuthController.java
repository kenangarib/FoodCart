package com.ecommerce.customer.controller;

import com.ecommerce.library.dto.CustomerDto;
import com.ecommerce.library.model.Customer;
import com.ecommerce.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;



    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @GetMapping("/signupOrLogin")
    public String register(Model model) {
        model.addAttribute("customerDto", new CustomerDto());


        return "signup";
    }
*/
   /* @RequestMapping(value = "/signupOrLogin", method = RequestMethod.GET)
    public String signupOrLogin(Model model) {
        model.addAttribute("customerDto", new CustomerDto());

        return "signup";
    }*/




    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "signup";
    }



    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                  BindingResult result,
                                  Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDto", customerDto);
                result.toString();
                return "signup";
            }
            Customer username = customerService.findByUsername(customerDto.getUsername());
            Customer email = customerService.findByEmail(customerDto.getEmail());
            if(email != null) {
                System.out.println("email not null");
                model.addAttribute("email", "Email have been registered!");
                model.addAttribute("customerDto",customerDto);
                return "signup";
            }
            else if(username != null){
                model.addAttribute("customerDto",customerDto);
                System.out.println("username  not null");
                model.addAttribute("username", "Username is took!");
                return "signup";
            }if(customerDto.getPassword().equals(customerDto.getRepeatPassword())){
                customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
                customerService.save(customerDto);
                model.addAttribute("success", "Register successfully");
                return "signup";
            }else{
                model.addAttribute("password", "Password is not same");
                model.addAttribute("customerDto",customerDto);
                return "signup";
            }

        }catch (Exception e){
            model.addAttribute("error", "Server have ran some problems");
            model.addAttribute("customerDto",customerDto);
        }
        return "signup";
    }
}