package br.com.fiap.challenge.restautant.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Restaurant Management API")
                .description("API para gerenciamento de restaurantes com suporte a operações CRUD de restaurantes, cardápios e tipos de comida")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Equipe FIAP Tech Challenge")
                    .url("https://github.com/Equipe-3-FIAP-POS-GRAD-ARC-JAVA/tech-challenge-fase-02"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")))
            .addServersItem(new Server()
                .url("http://localhost:8080")
                .description("Servidor de desenvolvimento"));
    }
}