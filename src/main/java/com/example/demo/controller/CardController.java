package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.model.Person;
import com.example.demo.repository.CardRepository;
import com.example.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/crear")
    public Mono<Void> post(@RequestBody Mono<Card> cardMono) {
        return cardService.insert(cardMono);
    }

    @GetMapping("/{number}")
    public Mono<Card> getCard(@PathVariable("number")Integer number){
        return cardService.findById(number);
    }

    @GetMapping("/all")
    public Flux<Card> list(){
        return cardService.listAll();
    }

    @PutMapping("/up")
    public Mono<Void> update(@RequestBody Mono<Card> cardMono) {
        return cardService.insert(cardMono);
    }

}
