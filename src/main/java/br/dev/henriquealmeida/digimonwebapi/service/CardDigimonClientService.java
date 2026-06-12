package br.dev.henriquealmeida.digimonwebapi.service;

import br.dev.henriquealmeida.digimonwebapi.client.Card;
import br.dev.henriquealmeida.digimonwebapi.dto.response.CardResponse;
import br.dev.henriquealmeida.digimonwebapi.exception.DigimonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static br.dev.henriquealmeida.digimonwebapi.util.Constants.DIGIMON_CARD_URL;
import static br.dev.henriquealmeida.digimonwebapi.util.Constants.ERROR_MESSAGE_INTERNAL_SERVER;
import static br.dev.henriquealmeida.digimonwebapi.util.Constants.PARAMETER_ERROR_MESSAGE;

@Slf4j
@Service
public class CardDigimonClientService {

    private final WebClient webClient;

    public CardDigimonClientService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(DIGIMON_CARD_URL).build();
    }

    public Flux<CardResponse> findCardByName(String cardName) {
        try {
            log.info("Search digimon card by [{}]", cardName);

            return webClient
                    .get()
                    .uri("search.php?n=" + cardName)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, error -> Mono.error(new RuntimeException(PARAMETER_ERROR_MESSAGE.formatted(cardName))))
                    .onStatus(HttpStatusCode::is5xxServerError, error -> Mono.error(new RuntimeException(ERROR_MESSAGE_INTERNAL_SERVER)))
                    .bodyToFlux(Card.class)
                    .map(card -> new CardResponse(
                            card.name(), card.type(), card.color(), card.stage(),
                            card.digiType(), card.attribute(), card.cardNumber(),
                            card.imageUrl(), card.cardSets())
                    );
        } catch (DigimonException e) {
            log.error("Invalid search card with [{}]", cardName, e);
            throw new DigimonException("Invalid card with name " + cardName);
        }
    }
}
