package com.ecommerce.library.service;

import com.ecommerce.library.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {
    List<Country> findAll();
}
