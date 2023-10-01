package br.dev.henriquealmeida.digimonwebapi.service;

import br.dev.henriquealmeida.digimonwebapi.dto.response.DigimonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DigimonClientService {

    @Value("${baseUrl}")
    private String BASE_URL;

    private final WebClient webClient;

    public DigimonClientService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://digimon-api.vercel.app/api/digimon/").build();
    }

    public Mono<DigimonResponse> findDigimonByName(String name) {
        log.info("Searching for digimon with name [{}]", name);

        return webClient
                .get()
                .uri("name/" + name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Check the given parameter: " + name)))
                .bodyToFlux(DigimonResponse.class)
                .next().log();
    }

    public Flux<DigimonResponse> findDigimonsByLevel(String level) {
        log.info("Searching for digimon with name [{}]", level);

        return webClient
                .get()
                .uri("level/" + level)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Check the level parameter")))
                .bodyToFlux(DigimonResponse.class).log();
    }
}
