package br.dev.henriquealmeida.digimonwebapi.service;

import br.dev.henriquealmeida.digimonwebapi.dto.response.CardResponse;
import br.dev.henriquealmeida.digimonwebapi.client.Card;
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
public class CardDigimonClientService {

    private final WebClient webClient;

    public CardDigimonClientService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(Constant.DIGIMON_CARD_URL).build();
    }

    public Flux<CardResponse> findCardByName(String cardName) {
        try {
            log.info("Search digimon card by [{}]", cardName);

            return webClient
                    .get()
                    .uri("search.php?n=" + cardName)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            error -> Mono.error(new RuntimeException("Check the given parameter: " + cardName)))
                    .bodyToFlux(Card.class)
                    .map(card -> new CardResponse(
                            card.name(), card.type(), card.color(), card.stage(),
                            card.digiType(), card.attribute(), card.cardNumber(),
                            card.imageUrl(), card.cardSets())
                    );
        } catch (InvalidDigimonNameException e) {
            log.error("Invalid search card with [{}]", cardName, e);
            throw new InvalidDigimonNameException("Invalid card with name " + cardName);
        }
    }
}
