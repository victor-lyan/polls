package com.example.polls.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Here we allow different http methods for all endpoints, because our frontend will be on a
 * different domain and to allow cross-domain ajax request we do this
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    private final long MAX_AGE_SECS = 3600;
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
            .maxAge(MAX_AGE_SECS);
    }
}
