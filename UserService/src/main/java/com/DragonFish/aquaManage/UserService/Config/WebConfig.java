package com.DragonFish.aquaManage.UserService.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS (Cross-Origin Resource Sharing) for the application.
     * This method allows the application to accept requests from specified origins.
     *
     * @param registry the CorsRegistry object used to add CORS mappings.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS settings to all endpoints
                .allowedOrigins("http://localhost:5173") // Allow this specific origin (frontend address)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allow any headers in the requests
                .allowCredentials(true); // Allow cookies and credentials to be included in cross-origin requests
    }
}
