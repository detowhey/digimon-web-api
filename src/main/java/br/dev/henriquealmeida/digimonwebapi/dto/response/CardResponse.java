package br.dev.henriquealmeida.digimonwebapi.dto.response;

import java.util.List;

public record CardResponse(
        String name,
        String type,
        String color,
        String state,
        String digiType,
        String attribute,
        String cardNumber,
        String imageUrl,
        List<String> cardSets
) {
}
