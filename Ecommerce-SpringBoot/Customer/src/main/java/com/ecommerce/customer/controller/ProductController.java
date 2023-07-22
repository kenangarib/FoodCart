package com.ecommerce.customer.controller;

import com.ecommerce.library.dto.CategoryDto;
import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Category;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.service.CategoryService;
import com.ecommerce.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/products")
    public String products(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.getAllProducts();
        List<Product> listViewProducts = productService.listViewProducts();
        model.addAttribute("categories", categoryDtoList);
        model.addAttribute("viewProducts", listViewProducts);
        model.addAttribute("products", products);
        model.addAttribute("title", "Shop");
        model.addAttribute("page", "Shop");
        return "shop";
    }

    @GetMapping("/find-product/{id}")
    public String findProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        Long categoryId = product.getCategory().getId();
        List<Product> products = productService.getRelatedProducts(categoryId);
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        return "product-detail";
    }

    @GetMapping("products-in-category/{id}")
    public String getProductsInCategory(@PathVariable("id") Long categoryId, Model model) {
        Category category = categoryService.findById(categoryId);
        List<CategoryDto> categories = categoryService.getCategoryAndProduct();
        List<Product> products = productService.getProductsInCategory(categoryId);
        List<Product> listViewProducts = productService.listViewProducts();
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        model.addAttribute("viewProducts", listViewProducts);
        model.addAttribute("title", "Shop");
        model.addAttribute("page", "Shop");
        return "products-in-category";
    }



    @GetMapping("/high-price")
    public String filterHighPrice(@RequestParam("filter") String filter,Model model){
        List<Category> categories = categoryService.findAllByActivated();
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.filterHighPrice(filter);
        List<Product> listViewProducts = productService.listViewProducts();
        model.addAttribute("products", products);
        //model.addAttribute("categories", categories);
        model.addAttribute("categories", categoryDtoList);
        model.addAttribute("viewProducts", listViewProducts);
        model.addAttribute("title", "Shop");
        model.addAttribute("page", "Shop");
        return "shop";
    }

    @GetMapping("/low-price")
    public String filterLowPrice(@RequestParam("filter") String filter,Model model){
        List<Category> categories = categoryService.findAllByActivated();
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.filterLowPrice(filter);
        List<Product> listViewProducts = productService.listViewProducts();
        model.addAttribute("products", products);
        //model.addAttribute("categories", categories);
        model.addAttribute("categories", categoryDtoList);
        model.addAttribute("viewProducts", listViewProducts);
        model.addAttribute("title", "Shop");
        model.addAttribute("page", "Shop");
        return "shop";
    }


    @GetMapping("/search-product")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
        List<CategoryDto> categoryDtos = categoryService.getCategoryAndProduct();
        List<ProductDto> productDtos = productService.searchProducts(keyword);
        List<Product> listView = productService.listViewProducts();
        model.addAttribute("productViews", listView);
        model.addAttribute("categories", categoryDtos);
        model.addAttribute("title", "Search Products");
        model.addAttribute("page", "Result Search");
        model.addAttribute("products", productDtos);
        return "shop";
    }






}
