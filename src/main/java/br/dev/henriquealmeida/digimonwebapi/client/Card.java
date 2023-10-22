package br.dev.henriquealmeida.digimonwebapi.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;

import java.util.List;
@Hidden
public record Card(
        String name,
        String type,
        String color,
        String stage,
        @JsonProperty("digi_type")
        String digiType,
        String attribute,
        @JsonProperty("cardnumber")
        String cardNumber,
        @JsonProperty("image_url")
        String imageUrl,
        @JsonProperty("card_sets")
        List<String> cardSets
) {
}
