package br.dev.henriquealmeida.digimonwebapi.dto.enu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "Level", description = "The levels that exist for digimon")
public enum Level {
    FRESH("fresh"),
    ROOKIE("rookie"),
    IN_TRAINING("inTraining"),
    CHAMPION("champion"),
    ULTIMATE("ultimate"),
    MEGA("mega"),
    ARMOR("armor");

    private final String level;

    Level(String level) {
        this.level = level;
    }
}
