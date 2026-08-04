package com.blog.demo.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // Inject the array of origins from your application.properties
    @Value("${app.cors.allowed-origins}")
    private String[] allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Applies CORS to all endpoints in your app
                .allowedOrigins(allowedOrigins) // Passes your array of domains
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allows all headers (Authorization, Content-Type, etc.)
                .allowCredentials(true) // Crucial: Allows withCredentials / cookies
                .maxAge(3600); // Caches the CORS preflight response for 1 hour
    }
}
