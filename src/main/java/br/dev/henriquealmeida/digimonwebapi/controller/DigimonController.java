package br.dev.henriquealmeida.digimonwebapi.controller;

import br.dev.henriquealmeida.digimonwebapi.dto.response.DigimonResponse;
import br.dev.henriquealmeida.digimonwebapi.service.DigimonClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/digimon")
public class DigimonController {

    DigimonClientService digimonClientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<DigimonResponse> getDigimonByName(@RequestParam String name) {
        return digimonClientService.findDigimonByName(name);
    }


}
