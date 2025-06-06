package com.example.lab7.Config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API de Estudiantes - PUCP")
                .version("1.0")
                .description("Laboratorio 7 - Gestión de Estudiantes con Spring Boot"));
    }
}

