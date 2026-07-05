package com.jadson.digitalbank.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI bancoDigitalOpenApi() {
        return new OpenAPI().info(new Info()
                .title("API Banco Digital")
                .description("API REST simplificada para transferências e consulta de movimentações")
                .version("1.0.0"));
    }
}
