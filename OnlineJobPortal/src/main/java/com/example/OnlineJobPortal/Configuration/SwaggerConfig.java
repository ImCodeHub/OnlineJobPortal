package com.example.OnlineJobPortal.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI setUpOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Online Job Portal System API")
                        .version("1.0")
                        .description("API documentation for the Online Job Portal with Authentication")
                        .contact(new Contact()
                                .name("Signimus")
                                .email("your.email@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
