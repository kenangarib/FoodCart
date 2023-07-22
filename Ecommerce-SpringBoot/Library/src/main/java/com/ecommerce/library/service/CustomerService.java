package com.ecommerce.library.service;

import com.ecommerce.library.dto.CustomerDto;
import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CustomerService {





    CustomerDto save(CustomerDto customerDto);
    Customer findByUsername(String username);
    Customer findByEmail(String email);

    Customer saveInfo(Customer customer);

    Customer changePass(CustomerDto customerDto);

    CustomerDto getCustomer(String username);

    Customer update(CustomerDto customerDto);
}
