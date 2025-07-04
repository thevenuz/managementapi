package config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all endpoints
                        .allowedOrigins("http://localhost:3000") // Allow React app
                        .allowedMethods("GET", "POST", "PUT", "DELETE","Options") // HTTP methods
//                        .allowedHeaders("*") // Allow all headers
                        .allowedHeaders("Authorization", "Content-Type", "Accept")
                        .allowCredentials(true); // Allow credentials like cookies
            }
        };
    }
}
