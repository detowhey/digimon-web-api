package br.dev.henriquealmeida.digimonwebapi.enu;

import lombok.Getter;

@Getter
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
