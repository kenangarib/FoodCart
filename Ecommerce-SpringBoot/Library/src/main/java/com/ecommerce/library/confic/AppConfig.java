package com.ecommerce.library.confic;

import com.ecommerce.library.service.WishlistService;
import com.ecommerce.library.service.impl.WishlistServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public WishlistService wishlistService() {
        return new WishlistServiceImpl();
    }
}
