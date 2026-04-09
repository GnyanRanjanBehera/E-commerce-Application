package com.ecommerce.ecommerce_product_service.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ProjectConfiguration {
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
