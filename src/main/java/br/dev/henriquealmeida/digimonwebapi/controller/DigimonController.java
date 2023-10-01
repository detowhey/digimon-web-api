package br.dev.henriquealmeida.digimonwebapi.controller;

import br.dev.henriquealmeida.digimonwebapi.dto.response.CardResponse;
import br.dev.henriquealmeida.digimonwebapi.dto.response.DigimonResponse;
import br.dev.henriquealmeida.digimonwebapi.enu.Level;
import br.dev.henriquealmeida.digimonwebapi.service.CardDigimonClientService;
import br.dev.henriquealmeida.digimonwebapi.service.DigimonClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Tag(name = "Digimon data", description = "REST service for searching digimon data")
@RestController
@RequestMapping(value = "/digimon", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DigimonController {

    private DigimonClientService digimonClientService;
    private CardDigimonClientService cardDigimonClientService;

    @Operation(
            summary = "Search a digimon by name",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found a digimon data by name",
                            content = @Content(schema = @Schema(implementation = DigimonResponse.class)))
            }
    )
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<DigimonResponse> getDigimonByName(@PathVariable String name) {
        return digimonClientService.findDigimonByName(name);
    }

    @Operation(
            summary = "Search a digimons by level",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found the digimons data by level",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = DigimonResponse.class))))
            }
    )
    @GetMapping("/level/{level}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<DigimonResponse> getDigimonsByLevel(@PathVariable Level level) {
        return digimonClientService.findDigimonsByLevel(level.getLevel());
    }

    @Operation(
            summary = "Search a digimon by name",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found the cards data by name",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CardResponse.class))))
            }
    )
    @GetMapping("/card")
    @ResponseStatus(HttpStatus.OK)
    public Flux<CardResponse> getDigimonCardsByCardName(@RequestParam String name) {
        return cardDigimonClientService.findCardByName(name);
    }
}
