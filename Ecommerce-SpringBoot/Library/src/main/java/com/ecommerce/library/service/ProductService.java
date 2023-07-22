package com.ecommerce.library.service;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public interface ProductService {

    //Admin
    List<ProductDto> findAll();
    List<ProductDto> allProduct();
    Product save(MultipartFile image, ProductDto productDto);
    Product update(MultipartFile imageProduct, ProductDto productDto);
    void deleteById(Long id);
    void enableById(Long id);
    ProductDto getById(Long id);
    Product findById(Long id);
    Page<ProductDto> pageProduct(int pageNo);

    Page<ProductDto> searchProducts(int pageNo,String keyword);


    //Customer

    List<ProductDto> findAllByCategory(String category);

    List<Product> getAllProducts();
    List<Product> listViewProducts();

    Product getProductById(Long id);
    List<Product> getRelatedProducts(Long categoryId);
    List<Product> getProductsInCategory(Long categoryId);

    List<Product> filterHighPrice(String filter);

    List<Product> filterLowPrice(String filter);

    List<ProductDto> searchProducts(String keyword);
}
