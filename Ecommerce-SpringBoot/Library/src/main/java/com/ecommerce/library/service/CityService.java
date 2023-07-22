package com.ecommerce.library.service;

import com.ecommerce.library.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    List<City> findAll();
}
