package br.dev.henriquealmeida.digimonwebapi.dto.response;

import br.dev.henriquealmeida.digimonwebapi.enu.Level;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Digimon", description = "Digimon properties")
@JsonPropertyOrder({"name", "level", "img"})
public record DigimonResponse(
        @Schema(description = "Digimon name", example = "Agumon")
        String name,
        @Schema(description = "Digimon level", example = "Rookie", implementation = Level.class)
        String level,
        @Schema(description = "Digimon image url", example = "https://digimon.shadowsmith.com/img/agumon.jpg")
        @JsonProperty(value = "img")
        String imageUrl) {
}
