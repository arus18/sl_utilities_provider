package com.example.sl_utilities_provider.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/service-photos/**")
                .addResourceLocations("file:/home/arus/Documents/spring and hibernate/sl_utilities_provider/service-photos/");
    }
}