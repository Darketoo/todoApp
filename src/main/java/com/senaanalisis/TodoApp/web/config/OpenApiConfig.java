package com.senaanalisis.TodoApp.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API TO-do app")
                        .version("1.0")
                        .description("API para el funcionamiento básico de una aplicación To-do con Spring Security")
                        .contact(new Contact()
                                .name("Repositorio API")
                                .url("https://github.com/Darketoo/todoApp")));
    }
}