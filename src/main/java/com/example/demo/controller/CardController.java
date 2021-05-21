package com.example.demo.controller;

import com.example.demo.model.Card;
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

    @PostMapping(value = "/crear")
    public Mono<Card> post(@RequestBody Mono<Card> cardMono) {
        return cardService.insert(cardMono);
    }

    @GetMapping("/{id}")
    public Mono<Card> getCard(@PathVariable("id")String id){
        return cardService.get(id);
    }

    @GetMapping("/all")
    public Flux<Card> list(){
        return cardService.listAll();
    }


    @GetMapping("/{type}/type")
    public Flux<Card> getType(@PathVariable("type") String type) {
        return cardService.findByType(type);
    }

    @PutMapping("/up")
    public Mono<Card> update(@RequestBody Mono<Card> cardMono) {
        return cardService.insert(cardMono);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return cardService.delete(id);
    }

}
