package br.dev.henriquealmeida.digimonwebapi.service;

import br.dev.henriquealmeida.digimonwebapi.dto.response.DigimonResponse;
import br.dev.henriquealmeida.digimonwebapi.exception.DigimonException;
import br.dev.henriquealmeida.digimonwebapi.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static br.dev.henriquealmeida.digimonwebapi.util.Constants.ERROR_MESSAGE_INTERNAL_SERVER;
import static br.dev.henriquealmeida.digimonwebapi.util.Constants.PARAMETER_ERROR_MESSAGE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
public class DigimonClientService {

    private final WebClient webClient;

    public DigimonClientService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(Constants.DIGIMON_DATA_URL).build();
    }

    public Mono<DigimonResponse> findDigimonByName(String name) {
        try {
            log.info("Searching for digimon with name [{}]", name);

            return webClient
                    .get()
                    .uri("name/" + name)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new RuntimeException(PARAMETER_ERROR_MESSAGE.formatted(name))))
                    .onStatus(HttpStatusCode::is5xxServerError, error -> Mono.error(new RuntimeException(ERROR_MESSAGE_INTERNAL_SERVER)))
                    .bodyToFlux(DigimonResponse.class)
                    .next();
        } catch (DigimonException e) {
            log.error("Invalid digimon name [{}]", name, e);
            throw new DigimonException("Error search digimon by name " + name);
        }
    }

    public Flux<DigimonResponse> findDigimonsByLevel(String level) {
        log.info("Searching for digimons with level [{}]", level);

        try {
            return webClient
                    .get()
                    .uri("level/" + level)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            error -> Mono.error(new DigimonException(PARAMETER_ERROR_MESSAGE.formatted(level)))
                    )
                    .bodyToFlux(DigimonResponse.class);
        } catch (DigimonException e) {
            log.error("Invalid level for Digimon search", e);
            throw new DigimonException("Check the level parameter: ");
        }
    }
}
