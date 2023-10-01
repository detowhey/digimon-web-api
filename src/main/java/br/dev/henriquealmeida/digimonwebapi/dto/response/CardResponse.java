package br.dev.henriquealmeida.digimonwebapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "Card", description = "Digimon card properties")
public record CardResponse(
        @Schema(description = "Digimon card name", example = "Agumon")
        String name,
        @Schema(description = "Digimon card type", example = "Digimon")
        String type,
        @Schema(description = "Digimon card color", example = "Green")
        String color,
        @Schema(description = "Digimon card stage", example = "Rookie", nullable = true)
        String stage,
        @Schema(description = "Digimon card digi type", example = "Reptile", nullable = true)
        String digiType,
        @Schema(description = "Digimon card attribute", example = "Vaccine", nullable = true)
        String attribute,
        @Schema(description = "Digimon card number code", example = "DM-008")
        String cardNumber,
        @Schema(description = "URL containing the card image", example = "https://images.digimoncard.io/images/cards/BO-218.jpg")
        String imageUrl,
        @Schema(description = "List containing the decks that can contain the card",
                example = "Event Pack 2, BT-02: Booster Ultimate Power")
        List<String> cardSets
) {
}
