package com.ecommerce.library.service;

import com.ecommerce.library.dto.CategoryDto;
import com.ecommerce.library.model.Category;
import com.ecommerce.library.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> findAll();
    Category save(Category category);

    Category findById(Long id);
    Category update(Category category);

    void deleteById(Long id);
    void enabledById(Long id);
    List<Category> findAllByActivated();

    //Customer
    List<CategoryDto> getCategoryAndProduct();

}
