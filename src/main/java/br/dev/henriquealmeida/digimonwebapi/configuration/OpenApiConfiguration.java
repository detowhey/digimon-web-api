package br.dev.henriquealmeida.digimonwebapi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition(servers = {
        @io.swagger.v3.oas.annotations.servers.Server(
                url = "/",
                description = "Digimon search data"
        )
})
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Digimon data").description("Digimon search data").version("v1"))
                .servers(List.of(new Server().url("http://localhost:8080").description("URL base")));
    }
}
