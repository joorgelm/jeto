package br.com.joorgelm.jeto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Value("#{systemEnvironment['JETO_APP_URL'] ?: 'DEFAULT_VALUE'}")
    private String jetoAppUrl;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(jetoAppUrl)
                        .allowedMethods("*");
            }
        };
    }
}
