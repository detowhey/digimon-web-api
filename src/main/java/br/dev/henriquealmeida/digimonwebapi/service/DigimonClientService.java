package br.dev.henriquealmeida.digimonwebapi.service;

import br.dev.henriquealmeida.digimonwebapi.dto.response.DigimonResponse;
import br.dev.henriquealmeida.digimonwebapi.exception.InvalidDigimonLevelException;
import br.dev.henriquealmeida.digimonwebapi.exception.InvalidDigimonNameException;
import br.dev.henriquealmeida.digimonwebapi.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DigimonClientService {

    private final WebClient webClient;

    public DigimonClientService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(Constant.DIGIMON_DATA_URL).build();
    }

    public Mono<DigimonResponse> findDigimonByName(String name) {
        try {
            log.info("Searching for digimon with name [{}]", name);

            return webClient
                    .get()
                    .uri("name/" + name)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            error -> Mono.error(new RuntimeException("Check the given parameter: " + name)))
                    .bodyToFlux(DigimonResponse.class)
                    .next();
        } catch (InvalidDigimonNameException e) {
            log.error("Invalid digimon name [{}]", name, e);
            throw new InvalidDigimonNameException("Error search digimon by name " + name);
        }
    }

    public Flux<DigimonResponse> findDigimonsByLevel(String level) {
        log.info("Searching for digimons with level [{}]", level);

        try {
            return webClient
                    .get()
                    .uri("level/" + level)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            error -> Mono.error(new InvalidDigimonLevelException("Check the level parameter: " + level))
                    )
                    .bodyToFlux(DigimonResponse.class);
        } catch (InvalidDigimonLevelException e) {
            log.error("Invalid level for Digimon search", e);
            throw new InvalidDigimonLevelException("Check the level parameter: ");
        }
    }
}
